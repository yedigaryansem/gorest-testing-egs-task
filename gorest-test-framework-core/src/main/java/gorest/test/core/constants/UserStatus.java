package gorest.test.core.constants;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum UserStatus {
    ACTIVE("active"),
    INACTIVE("inactive");

    public final String value;
}
