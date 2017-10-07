package com.cocktailsguru.app.health.domain;

import lombok.Data;

@Data
public class HealthStatus {
    private boolean springAlive;

    private boolean dbAlive;
}
