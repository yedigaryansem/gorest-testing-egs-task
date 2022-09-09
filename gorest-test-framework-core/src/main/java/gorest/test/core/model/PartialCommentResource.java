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
public class PartialCommentResource extends CommentResource {

    /**
     * An extension class for Lombok, to add custom methods to the builder.
     */
    public abstract static class PartialCommentResourceBuilder<C extends PartialCommentResource, B extends PartialCommentResourceBuilder<C, B>>
            extends CommentResourceBuilder<C, B> {

        public B copyFromResource(CommentResource source) {
            id(source.getId());
            postId(source.getPostId());
            name(source.getName());
            email(source.getEmail());
            body(source.getBody());
            return self();
        }
    }
}
