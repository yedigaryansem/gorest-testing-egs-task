package gorest.test.api.communication;

import lombok.NonNull;
import lombok.Singular;
import lombok.experimental.SuperBuilder;

import java.util.Map;

@SuperBuilder(builderMethodName = "basicBuilder")
public class BasicResponse {

    private final int statusCode;

    @NonNull
    @Singular
    private final Map<String, String> headers;

    public int getStatusCode() {
        return statusCode;
    }

    public String getHeader(String name) {
        return headers.get(name);
    }
}
