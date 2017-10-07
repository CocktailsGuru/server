package com.cocktailsguru.app.flyway;

import lombok.extern.slf4j.Slf4j;
import org.flywaydb.core.Flyway;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

@Component
@Slf4j
public class FlywayComponent {

    @Bean
    public Flyway flyway(DataSource dataSource) {
        Flyway flyway = new Flyway();

        // Point it to the database
        flyway.setDataSource(dataSource);

        // Start the migration
        int migrationCount = flyway.migrate();

        log.info("Flyway executed {} migrations", migrationCount);
        return flyway;
    }
}
