package gorest.test.api.communication;

import lombok.Getter;
import org.apache.hc.client5.http.classic.methods.HttpDelete;
import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.classic.methods.HttpPatch;
import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.classic.methods.HttpPut;
import org.apache.hc.core5.http.message.BasicClassicHttpRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.net.URI;
import java.net.URISyntaxException;

@Component
@Getter
public class RequestFactory {

    private final URI baseUrl;
    private final String apiToken;

    public RequestFactory(@Value("${gorest.api.url}") String baseUrl,
                          @Value("${gorest.api.token}") String apiToken) throws URISyntaxException {
        this.baseUrl = new URI(baseUrl);
        this.apiToken = apiToken;
    }

    /**
     * A handy method, that creates a new {@code RequestFactory} instance with updated {@link #baseUrl}.
     * The {@link #baseUrl} of the new RequestFactory is the result of appending {@code baseUrlPathSegment}
     * to the {@link #baseUrl} of the original {@code RequestFactory}
     * @param baseUrlPathSegment the path segment, that should be added to the current {@link #baseUrl}
     *                           in order to create a new {@code RequestFactory} instance
     * @return a new instance of {@code RequestFactory} with a new {@link #baseUrl} that looks like this:
     *          {@code "{old-base-url}/{base-url-path-segment}"}
     */
    public RequestFactory withAppendedBaseUrl(String baseUrlPathSegment) throws URISyntaxException {
        return new RequestFactory(baseUrl + "/" + baseUrlPathSegment, apiToken);
    }

    public HttpRequestBuilder<HttpGet> get() {
        return createBuilder(new HttpGet(baseUrl));
    }

    public HttpRequestBuilder<HttpPost> post() {
        return createBuilder(new HttpPost(baseUrl));
    }

    public HttpRequestBuilder<HttpPut> put() {
        return createBuilder(new HttpPut(baseUrl));
    }

    public HttpRequestBuilder<HttpPatch> patch() {
        return createBuilder(new HttpPatch(baseUrl));
    }

    public HttpRequestBuilder<HttpDelete> delete() {
        return createBuilder(new HttpDelete(baseUrl));
    }

    private <T extends BasicClassicHttpRequest> HttpRequestBuilder<T> createBuilder(T request) {
        try {
            return new HttpRequestBuilder<>(request)
                    .bearerToken(apiToken);
        } catch (URISyntaxException e) {
            /*
                The code will never reach to this catch clause, because, our baseUrl has already been
                tested in the constructor, and it is final. This means, that if we have an instance of
                HttpRequestBuilder<T>, then its baseUrl is a valid URI and URISyntaxException can never be thrown
             */
            throw new RuntimeException(e);
        }
    }
}
