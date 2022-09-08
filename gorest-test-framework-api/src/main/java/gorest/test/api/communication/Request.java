package gorest.test.api.communication;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import org.apache.hc.core5.http.message.BasicClassicHttpRequest;

import java.util.Optional;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class Request<R extends BasicClassicHttpRequest, Body> {
    @NonNull
    private final R httpRequest;
    private final Body bodyObject;

    public static <R extends BasicClassicHttpRequest> Request<R, Void> withoutBody(R httpRequest) {
        return new Request<>(httpRequest, null);
    }

    public static <R extends BasicClassicHttpRequest, Body> Request<R, Body> withBody(R httpRequest, Body bodyObject) {
        return new Request<>(httpRequest, bodyObject);
    }

    public Optional<Body> getBodyObject() {
        return Optional.ofNullable(bodyObject);
    }
}
