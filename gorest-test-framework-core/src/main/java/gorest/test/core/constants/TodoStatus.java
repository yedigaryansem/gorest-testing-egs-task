package gorest.test.core.constants;


import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum TodoStatus {
    PENDING("pending"),
    COMPLETED("completed");

    public final String value;
}