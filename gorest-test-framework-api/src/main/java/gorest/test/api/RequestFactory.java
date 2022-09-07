package gorest.test.api;

import lombok.Getter;
import org.apache.hc.client5.http.classic.methods.HttpDelete;
import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.classic.methods.HttpPatch;
import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.classic.methods.HttpPut;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.net.URISyntaxException;

@Component
@Getter
public class RequestFactory {

    private final String baseUrl;
    private final String apiToken;

    public RequestFactory(@Value("${gorest.api.url}") String baseUrl,
                          @Value("${gorest.api.token}") String apiToken) {
        this.baseUrl = baseUrl;
        this.apiToken = apiToken;
    }

    public HttpRequestBuilder<HttpGet> get() throws URISyntaxException {
        return new HttpRequestBuilder<>(new HttpGet(baseUrl))
                .bearerToken(apiToken);
    }

    public HttpRequestBuilder<HttpPost> post() throws URISyntaxException {
        return new HttpRequestBuilder<>(new HttpPost(baseUrl))
                .bearerToken(apiToken);
    }

    public HttpRequestBuilder<HttpPut> put() throws URISyntaxException {
        return new HttpRequestBuilder<>(new HttpPut(baseUrl))
                .bearerToken(apiToken);
    }

    public HttpRequestBuilder<HttpPatch> patch() throws URISyntaxException {
        return new HttpRequestBuilder<>(new HttpPatch(baseUrl))
                .bearerToken(apiToken);
    }

    public HttpRequestBuilder<HttpDelete> delete() throws URISyntaxException {
        return new HttpRequestBuilder<>(new HttpDelete(baseUrl))
                .bearerToken(apiToken);
    }
}
