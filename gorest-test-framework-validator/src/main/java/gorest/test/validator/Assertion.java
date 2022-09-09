package gorest.test.validator;

import gorest.test.core.model.CommentResource;
import gorest.test.core.model.PostResource;
import gorest.test.core.model.TodoResource;
import gorest.test.core.model.UserResource;

/**
 * A factory class, that helps to easily create validators for the business logic objects.
 */
public interface Assertion {
    static UserAssert of(UserResource actualUser) {
        return new UserAssert(actualUser);
    }

    static PostAssert of(PostResource actualPost) {
        return new PostAssert(actualPost);
    }

    static CommentAssert of(CommentResource actualComment) {
        return new CommentAssert(actualComment);
    }

    static TodoAssert of(TodoResource actualTodo) {
        return new TodoAssert(actualTodo);
    }
}
