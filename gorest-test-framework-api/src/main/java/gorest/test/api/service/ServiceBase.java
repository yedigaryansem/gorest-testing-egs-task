package gorest.test.api.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.exc.MismatchedInputException;
import gorest.test.api.communication.ApiError;
import gorest.test.api.communication.BasicResponse;
import gorest.test.api.communication.FieldValueApiError;
import gorest.test.api.communication.Request;
import gorest.test.api.communication.RequestFactory;
import gorest.test.api.communication.Response;
import gorest.test.api.exception.CannotConvertJsonToObjectException;
import gorest.test.api.exception.ObjectToJsonConversionException;
import gorest.test.api.exception.response.ApiErrorResponseException;
import gorest.test.api.exception.response.ApiFieldValueErrorResponseException;
import gorest.test.api.extension.JsonStringEntity;
import lombok.RequiredArgsConstructor;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.ClassicHttpRequest;
import org.apache.hc.core5.http.Header;
import org.apache.hc.core5.http.HttpEntity;
import org.apache.hc.core5.http.ParseException;
import org.apache.hc.core5.http.io.entity.EntityUtils;
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
    public <ResponseBody, RequestMethod extends BasicClassicHttpRequest>
    Response<ResponseBody> execute(Request<RequestMethod> request, Class<ResponseBody> objectClass) throws IOException, ParseException, ApiFieldValueErrorResponseException, ApiErrorResponseException {
        RequestMethod httpRequest = prepareRequest(request);
        final CloseableHttpResponse response = httpClient.execute(httpRequest);
        return prepareResponse(response, objectClass);
    }

    public <RequestMethod extends BasicClassicHttpRequest>
    BasicResponse execute(Request<RequestMethod> request) throws IOException {
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

    private <ResponseBody> Response<ResponseBody> prepareResponse(CloseableHttpResponse httpResponse, Class<ResponseBody> objectClass) throws IOException, ParseException, ApiFieldValueErrorResponseException, ApiErrorResponseException {
        final HttpEntity entity = httpResponse.getEntity();
        String responseJson = EntityUtils.toString(entity);
        final ResponseBody object = deserializeResponse(objectClass, responseJson, httpResponse);
        return createResponse(httpResponse, object);
    }

    private static <ResponseBody> Response<ResponseBody> createResponse(CloseableHttpResponse httpResponse, ResponseBody object) {
        final var responseBuilder = Response.<ResponseBody>builder()
                .resource(object)
                .statusCode(httpResponse.getCode());
        for (Header header : httpResponse.getHeaders()) {
            responseBuilder.header(header.getName(), header.getValue());
        }
        return responseBuilder.build();
    }

    private <ResponseBody> ResponseBody deserializeResponse(Class<ResponseBody> objectClass, String responseJson, CloseableHttpResponse httpResponse) throws JsonProcessingException, ApiErrorResponseException, ApiFieldValueErrorResponseException {
        try {
            return objectMapper.readValue(responseJson, objectClass);
        } catch (MismatchedInputException objectParsingException) {
            try {
                ApiError apiError = objectMapper.readValue(responseJson, ApiError.class);
                throw new ApiErrorResponseException(createResponse(httpResponse, apiError));
            } catch (MismatchedInputException errorParsingException) {
                try {
                    FieldValueApiError[] fieldErrors = objectMapper.readValue(responseJson, FieldValueApiError[].class);
                    throw new ApiFieldValueErrorResponseException(createResponse(httpResponse, fieldErrors));
                } catch (MismatchedInputException ignored) {
                }
            }

        }
        throw new CannotConvertJsonToObjectException("Cannot deserialize response to valid type or to any known error type: " + responseJson);
    }

    private <RequestMethod extends BasicClassicHttpRequest>
    RequestMethod prepareRequest(Request<RequestMethod> request) throws ObjectToJsonConversionException {
        RequestMethod httpRequest = request.getHttpRequest();
        request.getBodyObject()
                .map(this::toJsonString)
                .map(JsonStringEntity::new)
                .ifPresent(httpRequest::setEntity);
        return httpRequest;
    }
}
