package gorest.test.automation.utils;

import gorest.test.core.constants.UserGender;
import gorest.test.core.constants.UserStatus;
import gorest.test.core.model.UserResource;

import java.util.UUID;

public class Utils {
    public static UserResource initRandomUser() {
        return UserResource.builder()
                .name("TestUserName")
                .gender(UserGender.MALE)
                .email(UUID.randomUUID() + "TestMail@co.in")
                .status(UserStatus.INACTIVE).build();
    }
}
