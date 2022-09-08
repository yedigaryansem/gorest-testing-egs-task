package gorest.test.api.exception.response;

import gorest.test.api.communication.FieldValueApiError;
import lombok.Getter;

@Getter
public class ApiFieldValueErrorResponseException extends Exception {
    private final FieldValueApiError[] fieldApiErrors;

    public ApiFieldValueErrorResponseException(FieldValueApiError[] fieldApiErrors) {
        this.fieldApiErrors = fieldApiErrors;
    }
}
