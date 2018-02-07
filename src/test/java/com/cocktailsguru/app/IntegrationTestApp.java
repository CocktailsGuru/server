package com.cocktailsguru.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;

@SpringBootApplication
@EnableAutoConfiguration
@EnableConfigurationProperties
@EntityScan(
        basePackageClasses = {IntegrationTestApp.class, Jsr310JpaConverters.class}
)
public class IntegrationTestApp {
    public static void main(String[] args) {
        SpringApplication.run(IntegrationTestApp.class, args);
    }
}