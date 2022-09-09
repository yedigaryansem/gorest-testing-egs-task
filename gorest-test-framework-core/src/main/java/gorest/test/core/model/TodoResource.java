package gorest.test.core.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import gorest.test.core.constants.TodoStatus;
import gorest.test.core.metadata.HttpResourcePath;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

@Getter
@Setter
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@HttpResourcePath("todos")
public class TodoResource {
    @Builder.Default
    @JsonProperty("id")
    private Long id = null;

    @JsonProperty("user_id")
    private Long authorId;

    @JsonProperty("title")
    private String title;

    @JsonProperty("due_on")
    private String dueDate;

    @JsonProperty("status")
    private String status;

    /**
     * An extension class for Lombok, to add our custom date time setter to the builder.
     */
    public abstract static class TodoResourceBuilder<C extends TodoResource, B extends TodoResourceBuilder<C, B>> {
        public B status(TodoStatus status) {
            this.status = status.value;
            return self();
        }

        public B status(String status) {
            this.status = status;
            return self();
        }

        public B dueDate(String dueDate) {
            this.dueDate = dueDate;
            return self();
        }

        public B dueDate(ZonedDateTime dueDate) {
            this.dueDate = dueDate.truncatedTo(ChronoUnit.MILLIS).format(DateTimeFormatter.ISO_OFFSET_DATE_TIME);
            return self();
        }

        public B dueDateNow() {
            return dueDate(ZonedDateTime.now());
        }
    }

}
