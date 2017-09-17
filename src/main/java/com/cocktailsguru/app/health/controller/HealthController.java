package com.cocktailsguru.app.health.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthController {

    @RequestMapping("/health")
    public String checkHealthStatus() {
        return "Healthy";
    }
}
