package gorest.test.api.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanDefinitions {
    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper();
    }
}
