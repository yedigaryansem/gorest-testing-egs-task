package gorest.test.validator;

import gorest.test.core.model.TodoEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.assertj.core.api.LongAssert;
import org.assertj.core.api.StringAssert;

@AllArgsConstructor
@Getter
public class TodoAssert extends SoftAsserter<TodoEntity> {
    private final TodoEntity target;

    public LongAssert id() {
        return new LongAssert(target.getId());
    }

    public LongAssert authorId() {
        return new LongAssert(target.getAuthorId());
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

    public TodoAssert assertEqualsTo(TodoEntity expected) {
        id().isEqualTo(expected.getId());
        authorId().isEqualTo(expected.getAuthorId());
        title().isEqualTo(expected.getTitle());
        dueDate().isEqualTo(expected.getDueDate());
        status().isEqualTo(expected.getStatus());
        return this;
    }
}
