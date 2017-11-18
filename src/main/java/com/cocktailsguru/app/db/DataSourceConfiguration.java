package com.cocktailsguru.app.db;

import lombok.extern.slf4j.Slf4j;
import org.flywaydb.core.internal.util.jdbc.DriverDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import javax.sql.DataSource;

@Configuration
@Slf4j
public class DataSourceConfiguration {

    @Bean
    @Profile("develop")
    public DataSource defaultDataSource() {
        log.info("Providing local data source");
        return new DriverDataSource(
                Thread.currentThread().getContextClassLoader(),
                null,
                "jdbc:mysql://localhost:3306/cocktail_dev?useSSL=false",
                "root",
                "root"
        );
    }

    @Bean
    @Profile("test")
    public DataSource testDataSource() {
        log.info("Providing remote data source");
        return new DriverDataSource(
                Thread.currentThread().getContextClassLoader(),
                null,
                "jdbc:mysql://mysql-program:3306/cocktail_dev",
                "technical",
                "techUserPass123456"
        );
    }
}
