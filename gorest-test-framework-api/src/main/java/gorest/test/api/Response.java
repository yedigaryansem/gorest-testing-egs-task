package gorest.test.api;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NonNull;
import lombok.Singular;

import java.util.Map;

@AllArgsConstructor
@Builder
public class Response<Entity> {
    private final int statusCode;

    @NonNull
    @Singular
    private final Map<String, String> headers;

    @NonNull
    private final Entity entity;

    public String getHeader(String name) {
        return headers.get(name);
    }

    public Entity getEntity() {
        return entity;
    }

    public int getStatusCode() {
        return statusCode;
    }
}
