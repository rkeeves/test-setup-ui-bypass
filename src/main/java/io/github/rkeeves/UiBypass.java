package io.github.rkeeves;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableAutoConfiguration
public class UiBypass {

    public static void main(String[] args) {
        SpringApplication.run(UiBypass.class, args);
    }
}
