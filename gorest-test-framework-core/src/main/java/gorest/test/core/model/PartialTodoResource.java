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
public class PartialTodoResource extends TodoResource {

    /**
     * An extension class for Lombok, to add custom methods to the builder.
     */
    public abstract static class PartialTodoResourceBuilder<C extends PartialTodoResource, B extends PartialTodoResourceBuilder<C, B>>
            extends TodoResourceBuilder<C, B> {

        public B copyFromResource(TodoResource source) {
            id(source.getId());
            authorId(source.getAuthorId());
            title(source.getTitle());
            dueDate(source.getDueDate());
            status(source.getStatus());
            return self();
        }
    }

}
