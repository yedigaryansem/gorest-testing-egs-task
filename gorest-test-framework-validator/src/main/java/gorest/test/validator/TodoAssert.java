package gorest.test.validator;

import gorest.test.core.model.TodoResource;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.assertj.core.api.StringAssert;

@AllArgsConstructor
@Getter
public class TodoAssert extends SoftAsserter<TodoResource> {
    private final TodoResource target;

    public StringAssert id() {
        return new StringAssert(target.getId());
    }

    public StringAssert authorId() {
        return new StringAssert(target.getAuthorId());
    }

    public StringAssert title() {
        return new StringAssert(target.getTitle());
    }

    public StringAssert dueDate() {
        return new StringAssert(target.getDueDate());
    }

    public StringAssert status() {
        return new StringAssert(target.getStatus());
    }

    public TodoAssert assertEqualsTo(TodoResource expected) {
        id().isEqualTo(expected.getId());
        authorId().isEqualTo(expected.getAuthorId());
        title().isEqualTo(expected.getTitle());
        dueDate().isEqualTo(expected.getDueDate());
        status().isEqualTo(expected.getStatus());
        return this;
    }
}
