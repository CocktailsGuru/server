package com.cocktailsguru.app.flyway;

import lombok.extern.slf4j.Slf4j;
import org.flywaydb.core.Flyway;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class FlywayComponent {

    @Bean
    public Flyway createFlyway() {
        Flyway flyway = new Flyway();

        // Point it to the database
        flyway.setDataSource("jdbc:mysql://localhost:3306/cocktail_dev", "root", null);

        // Start the migration
        int migrationCount = flyway.migrate();

        log.info("Flyway executed {} migrations", migrationCount);
        return flyway;
    }
}
