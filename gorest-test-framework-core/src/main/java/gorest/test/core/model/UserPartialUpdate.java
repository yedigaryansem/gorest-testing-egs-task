package gorest.test.core.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserPartialUpdate extends UserResource {
    public abstract static class UserPartialUpdateBuilder<C extends UserPartialUpdate, B extends UserPartialUpdateBuilder<C, B>>
            extends UserResourceBuilder<C, B> {

        public B copyFromResource(UserResource source) {
            id(source.getId());
            name(source.getName());
            email(source.getEmail());
            gender(source.getGender());
            status(source.getStatus());
            return self();
        }
    }
}