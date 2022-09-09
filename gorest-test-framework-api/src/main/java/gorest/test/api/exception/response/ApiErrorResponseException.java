package gorest.test.api.exception.response;

import gorest.test.api.communication.ApiError;
import gorest.test.api.communication.Response;
import lombok.Getter;

@Getter
public class ApiErrorResponseException extends Exception {
    private final Response<ApiError> apiError;

    public ApiErrorResponseException(Response<ApiError> apiError) {
        super("API ERROR: " + apiError.getResource().getMessage());
        this.apiError = apiError;
    }
}
