package gorest.test.validator;

import gorest.test.core.model.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.assertj.core.api.LongAssert;
import org.assertj.core.api.StringAssert;

@AllArgsConstructor
@Getter
public class UserAssert extends SoftAsserter<UserEntity> {
    private final UserEntity target;

    public LongAssert id() {
        return new LongAssert(target.getId());
    }

    public StringAssert name() {
        return new StringAssert(target.getName());
    }

    public StringAssert email() {
        return new StringAssert(target.getEmail());
    }

    public StringAssert gender() {
        return new StringAssert(target.getGender());
    }

    public StringAssert status() {
        return new StringAssert(target.getStatus());
    }

    public UserAssert assertEqualsTo(UserEntity expected) {
        id().isEqualTo(expected.getId());
        name().isEqualTo(expected.getName());
        email().isEqualTo(expected.getEmail());
        gender().isEqualTo(expected.getGender());
        status().isEqualTo(expected.getStatus());
        return this;
    }
}
