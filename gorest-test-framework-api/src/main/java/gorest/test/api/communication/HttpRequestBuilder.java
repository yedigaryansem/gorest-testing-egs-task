package gorest.test.api.communication;

import lombok.Getter;
import org.apache.hc.core5.http.message.BasicClassicHttpRequest;
import org.apache.hc.core5.net.URIBuilder;

import java.net.URISyntaxException;

@Getter
public class HttpRequestBuilder<T extends BasicClassicHttpRequest> {
    private final T request;
    private final URIBuilder urlBuilder;
    private Object requestBody;

    public HttpRequestBuilder(T emptyRequest) throws URISyntaxException {
        this.request = emptyRequest;
        this.urlBuilder = new URIBuilder(emptyRequest.getUri());
    }

    public HttpRequestBuilder<T> bearerToken(String apiToken) {
        request.addHeader("Authorization", "Bearer " + apiToken);
        return this;
    }

    public HttpRequestBuilder<T> header(String name, String value) {
        request.addHeader(name, value);
        return this;
    }

    public HttpRequestBuilder<T> pathSegment(String pathSegment) {
        urlBuilder.setPath(urlBuilder.getPath() + "/" + pathSegment);
        return this;
    }

    public HttpRequestBuilder<T> pathSegment(Long pathSegment) {
        urlBuilder.setPath(urlBuilder.getPath() + "/" + pathSegment);
        return this;
    }

    public HttpRequestBuilder<T> param(String param, String value) {
        urlBuilder.addParameter(param, value);
        return this;
    }

    public HttpRequestBuilder<T> param(String param, Long value) {
        urlBuilder.addParameter(param, value.toString());
        return this;
    }

    public HttpRequestBuilder<T> body(Object bodyObject) {
        requestBody = bodyObject;
        return this;
    }

    public Request<T> build() throws URISyntaxException {
        request.setUri(urlBuilder.build());
        if (requestBody == null) {
            Request.withoutBody(request);
        }
        return Request.withBody(request, requestBody);
    }
}
