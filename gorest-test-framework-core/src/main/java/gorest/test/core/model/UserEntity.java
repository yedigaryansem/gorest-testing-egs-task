package gorest.test.core.model;

import gorest.test.core.constants.UserConstants;
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
        public UserEntityBuilder male() {
            this.gender = UserConstants.Gender.MALE;
            return this;
        }

        public UserEntityBuilder female() {
            this.gender = UserConstants.Gender.FEMALE;
            return this;
        }

        public UserEntityBuilder active() {
            this.status = UserConstants.Status.ACTIVE;
            return this;
        }

        public UserEntityBuilder inactive() {
            this.status = UserConstants.Status.INACTIVE;
            return this;
        }
    }
}