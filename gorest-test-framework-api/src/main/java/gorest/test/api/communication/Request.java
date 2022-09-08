package gorest.test.api.communication;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import org.apache.hc.core5.http.message.BasicClassicHttpRequest;

import java.util.Optional;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class Request<R extends BasicClassicHttpRequest> {
    @NonNull
    private final R httpRequest;
    private final Object bodyObject;

    public static <R extends BasicClassicHttpRequest> Request<R> withoutBody(R httpRequest) {
        return new Request<>(httpRequest, null);
    }

    public static <R extends BasicClassicHttpRequest> Request<R> withBody(R httpRequest, Object bodyObject) {
        return new Request<>(httpRequest, bodyObject);
    }

    public Optional<Object> getBodyObject() {
        return Optional.ofNullable(bodyObject);
    }
}
