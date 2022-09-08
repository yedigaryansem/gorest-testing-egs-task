package gorest.test.api.communication;

import lombok.NonNull;
import lombok.experimental.SuperBuilder;

@SuperBuilder
public class Response<Resource> extends BasicResponse {

    @NonNull
    private final Resource resource;

    public @NonNull Resource getResource() {
        return resource;
    }
}
