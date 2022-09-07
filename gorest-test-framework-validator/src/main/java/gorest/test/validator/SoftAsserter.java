package gorest.test.validator;

import org.assertj.core.api.SoftAssertions;

import java.util.function.BiConsumer;

public abstract class SoftAsserter<T> {

    public void assertSoftly(BiConsumer<SoftAssertions, T> assertions) {
        SoftAssertions softly = new SoftAssertions();
        assertions.accept(softly, getTarget());
        softly.assertAll();
    }

    protected abstract T getTarget();
}
