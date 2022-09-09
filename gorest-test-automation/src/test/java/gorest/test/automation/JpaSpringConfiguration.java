package gorest.test.automation;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@ComponentScan
@EntityScan("gorest.test.automation.db.entity")
@EnableJpaRepositories(basePackages = {
        "gorest.test.automation.db"
})
@EnableAutoConfiguration
public class JpaSpringConfiguration {
}
