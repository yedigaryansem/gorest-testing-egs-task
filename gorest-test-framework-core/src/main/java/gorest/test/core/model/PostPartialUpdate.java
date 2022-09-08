package gorest.test.core.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PostPartialUpdate extends PostResource {
    public abstract static class PostPartialUpdateBuilder<C extends PostPartialUpdate, B extends PostPartialUpdateBuilder<C, B>>
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
