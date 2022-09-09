package gorest.test.automation;


import org.springframework.context.annotation.Import;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;


@ContextConfiguration({
        "classpath:gorest-test-framework-api-springconfig.xml",
        "classpath:gorest-test-framework-core-springconfig.xml",
        "classpath:gorest-test-framework-validator-springconfig.xml"
})
@TestPropertySource(locations = "classpath:application.properties")
@Import(JpaSpringConfiguration.class)
public class TestBase extends AbstractTestNGSpringContextTests {
    public static final String SPRING_CONTEXT_INIT_BEFORE_CLASS = "springTestContextPrepareTestInstance";
    public static final String SPRING_CONTEXT_INIT_BEFORE_METHOD = "springTestContextBeforeTestMethod";
    public static final String SPRING_CONTEXT_INIT_AFTER_CLASS = "springTestContextAfterTestClass";
    public static final String SPRING_CONTEXT_INIT_AFTER_METHOD = "springTestContextAfterTestMethod";
}
