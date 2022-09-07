package gorest.test.api.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import gorest.test.api.RequestFactory;
import gorest.test.api.Response;
import lombok.RequiredArgsConstructor;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.ClassicHttpRequest;
import org.apache.hc.core5.http.Header;
import org.apache.hc.core5.http.HttpEntity;

import java.io.IOException;

@RequiredArgsConstructor
public class ServiceBase implements AutoCloseable {
    private final CloseableHttpClient httpClient = HttpClients.createDefault();

    private final RequestFactory requestFactory;

    private final ObjectMapper objectMapper;

    public String getBaseUrl() {
        return requestFactory.getBaseUrl();
    }

    public String getApiToken() {
        return requestFactory.getApiToken();
    }

    /**
     * Performs the request and also deserializes the response into the requested object type.
     *
     * @param request     the request to execute
     * @param objectClass the type of the target object, to which requester wants to deserialize the response body
     * @param <T>         template type of the object, to which the body should be deserialized
     * @return an object, that contains info about the response HTTP details (headers, status code) and deserialized object from the body of that response
     * @throws IOException if the request cannot be executed, see {@link CloseableHttpClient#execute(ClassicHttpRequest)} for details
     */
    public <T> Response<T> execute(ClassicHttpRequest request, Class<T> objectClass) throws IOException {
        final CloseableHttpResponse response = httpClient.execute(request);
        final HttpEntity entity = response.getEntity();
        final T object = objectMapper.readValue(entity.getContent(), objectClass);
        final Response.ResponseBuilder<T> responseBuilder = Response.<T>builder()
                .entity(object)
                .statusCode(response.getCode());
        for (Header header : response.getHeaders()) {
            responseBuilder.header(header.getName(), header.getValue());
        }
        return responseBuilder.build();
    }

    protected RequestFactory requestFactory() {
        return requestFactory;
    }

    @Override
    public void close() throws Exception {
        httpClient.close();
    }
}
