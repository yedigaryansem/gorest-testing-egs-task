package gorest.test.core.constants;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum UserGender {
    MALE("male"),
    FEMALE("female");

    public final String value;
}