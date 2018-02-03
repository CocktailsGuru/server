package com.cocktailsguru.app.health.controller;

import com.cocktailsguru.app.health.domain.HealthStatus;
import com.cocktailsguru.app.health.dto.HealthResponseDto;
import com.cocktailsguru.app.health.service.HealthService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthController {
    public static final String HEALTH_PATH = "/healthCustom";

    private HealthService healthService;
    private final ModelMapper modelMapper;

    @Autowired
    public HealthController(HealthService healthService, ModelMapper modelMapper) {
        this.healthService = healthService;
        this.modelMapper = modelMapper;
    }

    @RequestMapping(value = HEALTH_PATH, produces = "application/json", method = RequestMethod.GET)
    public HealthResponseDto checkHealthStatus() {
        HealthStatus status = healthService.checkHealth();
        return modelMapper.map(status, HealthResponseDto.class);
    }
}
