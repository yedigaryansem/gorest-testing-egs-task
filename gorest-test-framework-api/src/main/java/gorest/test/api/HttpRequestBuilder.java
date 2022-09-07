package gorest.test.api;

import lombok.Getter;
import org.apache.hc.core5.http.message.BasicHttpRequest;
import org.apache.hc.core5.net.URIBuilder;

import java.net.URISyntaxException;

@Getter
public class HttpRequestBuilder<T extends BasicHttpRequest> {
    private final T request;
    private final URIBuilder urlBuilder;

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

    public HttpRequestBuilder<T> pathElement(String pathElement) {
        urlBuilder.setPath(urlBuilder.getPath() + "/" + pathElement);
        return this;
    }

    public HttpRequestBuilder<T> pathElement(Long pathElement) {
        urlBuilder.setPath(urlBuilder.getPath() + "/" + pathElement);
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

    public T build() throws URISyntaxException {
        request.setUri(urlBuilder.build());
        return request;
    }
}
