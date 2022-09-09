package gorest.test.automation.utils;

import gorest.test.core.constants.TodoStatus;
import gorest.test.core.constants.UserGender;
import gorest.test.core.constants.UserStatus;
import gorest.test.core.model.TodoResource;
import gorest.test.core.model.UserResource;

import java.util.UUID;

public class Utils {
    public static UserResource initRandomUser() {
        return UserResource.builder()
                .name("TestUserName")
                .gender(UserGender.MALE)
                .email(System.currentTimeMillis() + "_TestMail@co.in")
                .status(UserStatus.INACTIVE).build();
    }

    public static TodoResource initRandomTodo(String authorId) {
        return TodoResource.builder()
                .authorId(authorId)
                .title("test-title " + System.currentTimeMillis())
                .dueDate("2022-09-09T21:54:41.147+05:30")
                .status(TodoStatus.PENDING)
                .build();
    }
}
