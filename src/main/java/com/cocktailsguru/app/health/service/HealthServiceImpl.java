package com.cocktailsguru.app.health.service;

import com.cocktailsguru.app.health.domain.HealthStatus;
import com.cocktailsguru.app.person.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HealthServiceImpl implements HealthService {

    private final PersonRepository personRepository;


    @Autowired
    public HealthServiceImpl(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Override
    public HealthStatus checkHealth() {
        HealthStatus status = new HealthStatus();

        status.setSpringAlive(true);
        status.setDbAlive(personRepository.findOne(1) != null);

        return status;
    }
}
