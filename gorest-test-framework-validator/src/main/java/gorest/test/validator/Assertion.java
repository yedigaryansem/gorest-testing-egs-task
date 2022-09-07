package gorest.test.validator;

import gorest.test.core.model.CommentEntity;
import gorest.test.core.model.PostEntity;
import gorest.test.core.model.TodoEntity;
import gorest.test.core.model.UserEntity;

/**
 * A factory class, that helps to easily create validators for the business logic objects.
 */
public interface Assertion {
    static UserAssert of(UserEntity targetUser) {
        return new UserAssert(targetUser);
    }

    static PostAssert of(PostEntity targetPost) {
        return new PostAssert(targetPost);
    }

    static CommentAssert of(CommentEntity targetComment) {
        return new CommentAssert(targetComment);
    }

    static TodoAssert of(TodoEntity targetTodo) {
        return new TodoAssert(targetTodo);
    }
}
