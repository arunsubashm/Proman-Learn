package org.example.proman.service;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("org.example.proman.service")
@EntityScan("com.upgrad.proman.service.entity")
public class ServiceConfiguration {
}
