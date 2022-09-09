package gorest.test.api.exception.response;

import gorest.test.api.communication.FieldValueApiError;
import gorest.test.api.communication.Response;
import lombok.Getter;

import java.util.Arrays;

@Getter
public class ApiFieldValueErrorResponseException extends Exception {
    private final Response<FieldValueApiError[]> fieldApiErrors;

    public ApiFieldValueErrorResponseException(Response<FieldValueApiError[]> fieldApiErrors) {
        super("API FIELD ERRORS: " + Arrays.toString(fieldApiErrors.getResource()));
        this.fieldApiErrors = fieldApiErrors;
    }
}
