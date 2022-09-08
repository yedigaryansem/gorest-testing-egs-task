package gorest.test.validator;

import gorest.test.core.model.CommentResource;
import gorest.test.core.model.PostResource;
import gorest.test.core.model.TodoResource;
import gorest.test.core.model.UserResource;

/**
 * A factory class, that helps to easily create validators for the business logic objects.
 */
public interface Assertion {
    static UserAssert of(UserResource targetUser) {
        return new UserAssert(targetUser);
    }

    static PostAssert of(PostResource targetPost) {
        return new PostAssert(targetPost);
    }

    static CommentAssert of(CommentResource targetComment) {
        return new CommentAssert(targetComment);
    }

    static TodoAssert of(TodoResource targetTodo) {
        return new TodoAssert(targetTodo);
    }
}
