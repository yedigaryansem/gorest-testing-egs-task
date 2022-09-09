package gorest.test.validator;

import gorest.test.core.model.CommentResource;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.assertj.core.api.LongAssert;
import org.assertj.core.api.StringAssert;

@AllArgsConstructor
@Getter
public class CommentAssert extends SoftAsserter<CommentResource> {
    private CommentResource target;

    public StringAssert id() {
        return new StringAssert(target.getId());
    }

    public StringAssert postId() {
        return new StringAssert(target.getPostId());
    }

    public StringAssert name() {
        return new StringAssert(target.getName());
    }

    public StringAssert email() {
        return new StringAssert(target.getEmail());
    }

    public StringAssert body() {
        return new StringAssert(target.getBody());
    }

    public CommentAssert assertEqualsTo(CommentResource expected) {
        id().isEqualTo(expected.getId());
        postId().isEqualTo(expected.getPostId());
        name().isEqualTo(expected.getName());
        email().isEqualTo(expected.getEmail());
        body().isEqualTo(expected.getBody());
        return this;
    }
}
