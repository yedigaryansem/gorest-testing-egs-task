package gorest.test.api.exception.response;

import gorest.test.api.communication.ApiError;
import lombok.Getter;

@Getter
public class ApiErrorResponseException extends Exception {
    private final ApiError apiError;

    public ApiErrorResponseException(ApiError apiError) {
        this.apiError = apiError;
    }
}
