package gorest.test.core.model;

import gorest.test.core.constants.TodoStatus;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

@Getter
@Setter
@Builder
public class TodoEntity {
    @Builder.Default
    private final Long id = null;
    private Long authorId;
    private String title;
    private String dueDate;
    private String status;

    /**
     * An extension class for Lombok, to add our custom date time setter to the builder.
     */
    public static class TodoEntityBuilder {
        public TodoEntityBuilder status(TodoStatus status) {
            this.status = status.value;
            return this;
        }

        public TodoEntityBuilder status(String status) {
            this.status = status;
            return this;
        }

        public TodoEntityBuilder dueDate(String dueDate) {
            this.dueDate = dueDate;
            return this;
        }

        public TodoEntityBuilder dueDate(ZonedDateTime dueDate) {
            this.dueDate = dueDate.format(DateTimeFormatter.ISO_OFFSET_DATE_TIME);
            return this;
        }

        public TodoEntityBuilder dueDateNow() {
            return dueDate(ZonedDateTime.now());
        }
    }

}
