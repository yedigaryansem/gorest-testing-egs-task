package gorest.test.validator;

import gorest.test.core.model.PostResource;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.assertj.core.api.LongAssert;
import org.assertj.core.api.StringAssert;

@AllArgsConstructor
@Getter
public class PostAssert extends SoftAsserter<PostResource> {
    private final PostResource target;

    public LongAssert id() {
        return new LongAssert(target.getId());
    }

    public LongAssert authorId() {
        return new LongAssert(target.getAuthorId());
    }

    public StringAssert title() {
        return new StringAssert(target.getTitle());
    }

    public StringAssert body() {
        return new StringAssert(target.getBody());
    }

    public PostAssert assertEqualsTo(PostResource expected) {
        id().isEqualTo(expected.getId());
        authorId().isEqualTo(expected.getAuthorId());
        title().isEqualTo(expected.getTitle());
        body().isEqualTo(expected.getBody());
        return this;
    }
}
