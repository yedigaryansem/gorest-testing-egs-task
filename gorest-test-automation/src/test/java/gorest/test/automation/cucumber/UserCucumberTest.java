package gorest.test.automation.cucumber;

import gorest.test.automation.JpaSpringConfiguration;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import io.cucumber.spring.CucumberContextConfiguration;
import org.junit.runner.RunWith;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/resources/cucumber/features/user.feature")
@CucumberContextConfiguration
@ContextConfiguration({
        "classpath:gorest-test-framework-api-springconfig.xml",
        "classpath:gorest-test-framework-core-springconfig.xml",
        "classpath:gorest-test-framework-validator-springconfig.xml"
})
@TestPropertySource(locations = "classpath:application.properties")
@Import(JpaSpringConfiguration.class)
public class UserCucumberTest {
}
