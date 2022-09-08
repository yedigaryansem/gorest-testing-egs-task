package gorest.test.api.exception;

public class CannotConvertJsonToObjectException extends RuntimeException {
    public CannotConvertJsonToObjectException(String message) {
        super(message);
    }
}
