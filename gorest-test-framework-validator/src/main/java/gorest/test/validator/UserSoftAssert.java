package gorest.test.validator;

import gorest.test.core.model.UserResource;
import lombok.RequiredArgsConstructor;
import org.assertj.core.api.SoftAssertions;
import org.assertj.core.api.StringAssert;

@RequiredArgsConstructor
public class UserSoftAssert {
    private final UserResource target;
    private final SoftAssertions softly = new SoftAssertions();

    public StringAssert id() {
        return new StringAssert(target.getId());
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

    public UserSoftAssert validateEquals(UserResource expected) {
        id().isEqualTo(expected.getId());
        name().isEqualTo(expected.getName());
        email().isEqualTo(expected.getEmail());
        gender().isEqualTo(expected.getGender());
        status().isEqualTo(expected.getStatus());
        return this;
    }
}
