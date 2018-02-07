package com.cocktailsguru.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;

@SpringBootApplication
@EntityScan(
        basePackageClasses = {CocktailsGuruApplication.class, Jsr310JpaConverters.class}
)
public class CocktailsGuruApplication {

    public static void main(String[] args) {
        SpringApplication.run(CocktailsGuruApplication.class, args);
    }
}
