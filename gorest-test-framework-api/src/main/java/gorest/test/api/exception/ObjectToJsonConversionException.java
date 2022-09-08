package gorest.test.api.exception;

public class ObjectToJsonConversionException extends RuntimeException {
    public ObjectToJsonConversionException(String message, Throwable cause) {
        super(message, cause);
    }
}
