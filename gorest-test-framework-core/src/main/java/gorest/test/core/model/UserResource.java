package gorest.test.core.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import gorest.test.core.constants.UserGender;
import gorest.test.core.constants.UserStatus;
import gorest.test.core.metadata.HttpResourcePath;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@HttpResourcePath("users")
public class UserResource {
    @Builder.Default
    @JsonProperty("id")
    private Long id = null;

    @JsonProperty("name")
    private String name;

    @JsonProperty("email")
    private String email;

    @JsonProperty("gender")
    private String gender;

    @JsonProperty("status")
    private String status;

    /**
     * An extension class for Lombok, to add our custom methods to the builder.
     */
    public abstract static class UserResourceBuilder<C extends UserResource, B extends UserResourceBuilder<C, B>> {
        public B gender(UserGender gender) {
            this.gender = gender.value;
            return self();
        }

        public B status(UserStatus status) {
            this.status = status.value;
            return self();
        }

        public B gender(String gender) {
            this.gender = gender;
            return self();
        }

        public B status(String status) {
            this.status = status;
            return self();
        }
    }
}