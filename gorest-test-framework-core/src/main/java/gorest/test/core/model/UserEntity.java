package gorest.test.core.model;

import gorest.test.core.constants.UserGender;
import gorest.test.core.constants.UserStatus;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class UserEntity {
    @Builder.Default
    private final Long id = null;
    private String name;
    private String email;
    private String gender;
    private String status;

    /**
     * An extension class for Lombok, to add our custom methods to the builder.
     */
    public static class UserEntityBuilder {
        public UserEntityBuilder gender(UserGender gender) {
            this.gender = gender.value;
            return this;
        }

        public UserEntityBuilder status(UserStatus status) {
            this.status = status.value;
            return this;
        }

        public UserEntityBuilder gender(String gender) {
            this.gender = gender;
            return this;
        }

        public UserEntityBuilder status(String status) {
            this.status = status;
            return this;
        }

    }
}