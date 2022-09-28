package io.github.rkeeves.context;

import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.boot.test.context.SpringBootTest;

@CucumberContextConfiguration
@SpringBootTest(classes = SpringContextConfig.class)
public class CucumberContextConfig {
}
