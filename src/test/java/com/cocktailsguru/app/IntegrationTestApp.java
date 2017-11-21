package com.cocktailsguru.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableAutoConfiguration
@EnableConfigurationProperties
public class IntegrationTestApp {
    public static void main(String[] args) {
        SpringApplication.run(IntegrationTestApp.class, args);
    }
}