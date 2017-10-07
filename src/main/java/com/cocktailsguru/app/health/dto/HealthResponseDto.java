package com.cocktailsguru.app.health.dto;

import lombok.Data;

@Data
public class HealthResponseDto {

    private boolean springAlive;

    private boolean dbAlive;
}
