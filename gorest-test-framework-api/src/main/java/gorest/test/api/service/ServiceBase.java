package gorest.test.api.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import gorest.test.api.communication.BasicResponse;
import gorest.test.api.communication.Request;
import gorest.test.api.communication.RequestFactory;
import gorest.test.api.communication.Response;
import gorest.test.api.exception.ObjectToJsonConversionException;
import gorest.test.api.extension.JsonStringEntity;
import lombok.RequiredArgsConstructor;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.ClassicHttpRequest;
import org.apache.hc.core5.http.Header;
import org.apache.hc.core5.http.HttpEntity;
import org.apache.hc.core5.http.message.BasicClassicHttpRequest;

import java.io.IOException;

@RequiredArgsConstructor
public class ServiceBase implements AutoCloseable {
    private final CloseableHttpClient httpClient = HttpClients.createDefault();

    private final RequestFactory requestFactory;

    private final ObjectMapper objectMapper;

    /**
     * Performs the request and also deserializes the response into the requested object type.
     *
     * @param request     the request to execute
     * @param objectClass the type of the target object, to which requester wants to deserialize the response body
     * @param <ResponseBody>         template type of the object, to which the body should be deserialized
     * @return an object, that contains info about the response HTTP details (headers, status code) and deserialized object from the body of that response
     * @throws IOException if the request cannot be executed, see {@link CloseableHttpClient#execute(ClassicHttpRequest)} for details
     */
    public <ResponseBody, RequestBody, RequestMethod extends BasicClassicHttpRequest>
    Response<ResponseBody> execute(Request<RequestMethod, RequestBody> request, Class<ResponseBody> objectClass) throws IOException {
        RequestMethod httpRequest = prepareRequest(request);
        final CloseableHttpResponse response = httpClient.execute(httpRequest);
        return prepareResponse(response, objectClass);
    }

    public <RequestBody, RequestMethod extends BasicClassicHttpRequest>
    BasicResponse execute(Request<RequestMethod, RequestBody> request) throws IOException {
        RequestMethod httpRequest = prepareRequest(request);
        final CloseableHttpResponse response = httpClient.execute(httpRequest);
        final var responseBuilder = BasicResponse.basicBuilder()
                .statusCode(response.getCode());
        for (Header header : response.getHeaders()) {
            responseBuilder.header(header.getName(), header.getValue());
        }
        return responseBuilder.build();
    }

    @Override
    public void close() throws Exception {
        httpClient.close();
    }

    protected RequestFactory requestFactory() {
        return requestFactory;
    }

    /**
     * A helper method to be used during object to json conversion inside lambdas. This will catch the original exception
     * thrown by Jackson library, and will wrap it with a RuntimeException. This gives opportunity to write a laconic
     * code when using lambdas, in places where checked exceptions cannot be thrown inside the lambda.
     * @param object the object, that will be converted to JSON String using {@link #objectMapper}
     * @throws ObjectToJsonConversionException if {@link ObjectMapper#writeValueAsString(Object)} throws
     *                                         {@link JsonProcessingException}, we rethrow wrapped {@link ObjectToJsonConversionException}
     */
    private <T> String toJsonString(T object) throws ObjectToJsonConversionException {
        try {
            return objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            throw new ObjectToJsonConversionException("Exception during request serialization", e);
        }
    }

    private <ResponseBody> Response<ResponseBody> prepareResponse(CloseableHttpResponse response, Class<ResponseBody> objectClass) throws IOException {
        final HttpEntity entity = response.getEntity();
        final ResponseBody object = objectMapper.readValue(entity.getContent(), objectClass);
        final var responseBuilder = Response.<ResponseBody>builder()
                .resource(object)
                .statusCode(response.getCode());
        for (Header header : response.getHeaders()) {
            responseBuilder.header(header.getName(), header.getValue());
        }
        return responseBuilder.build();
    }

    private <RequestMethod extends BasicClassicHttpRequest, RequestBody>
    RequestMethod prepareRequest(Request<RequestMethod, RequestBody> request) throws ObjectToJsonConversionException {
        RequestMethod httpRequest = request.getHttpRequest();
        request.getBodyObject()
                .map(this::toJsonString)
                .map(JsonStringEntity::new)
                .ifPresent(httpRequest::setEntity);
        return httpRequest;
    }
}
