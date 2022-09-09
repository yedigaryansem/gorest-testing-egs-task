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
public class PartialPostResource extends PostResource {

    /**
     * An extension class for Lombok, to add custom methods to the builder.
     */
    public abstract static class PartialPostResourceBuilder<C extends PartialPostResource, B extends PartialPostResourceBuilder<C, B>>
            extends PostResourceBuilder<C, B> {

        public B copyFromResource(PostResource source) {
            id(source.getId());
            authorId(source.getAuthorId());
            title(source.getTitle());
            body(source.getBody());
            return self();
        }
    }
}
