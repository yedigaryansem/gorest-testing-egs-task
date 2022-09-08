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
public class TodoPartialUpdate extends TodoResource {

    /**
     * An extension class for Lombok, to add our custom date time setter to the builder.
     */
    public abstract static class TodoPartialUpdateBuilder<C extends TodoPartialUpdate, B extends TodoPartialUpdateBuilder<C, B>>
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
