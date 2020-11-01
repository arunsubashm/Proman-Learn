package org.example.proman.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

import java.util.ServiceConfigurationError;

@SpringBootApplication
@Import(ServiceConfigurationError.class)
public class PromanApiApplication {
    public static void main(String[] args) {
        SpringApplication.run(PromanApiApplication.class, args);
    }
}
