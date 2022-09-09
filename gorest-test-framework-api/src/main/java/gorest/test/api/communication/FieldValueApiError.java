package gorest.test.api.communication;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class FieldValueApiError extends ApiError {
    private String field;

    public FieldValueApiError(String message, String field) {
        super(message);
        this.field = field;
    }

    @Override
    public String toString() {
        return "{" +
                "field='" + field + "'\n" +
                "message='" + getMessage() + "'" +
                '}';
    }
}
