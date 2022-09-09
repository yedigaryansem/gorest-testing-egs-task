package gorest.test.core.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PartialUserResource extends UserResource {

    /**
     * An extension class for Lombok, to add custom methods to the builder.
     */
    public abstract static class PartialUserResourceBuilder<C extends PartialUserResource, B extends PartialUserResourceBuilder<C, B>>
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