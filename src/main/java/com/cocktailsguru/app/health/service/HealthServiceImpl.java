package com.cocktailsguru.app.health.service;

import com.cocktailsguru.app.health.domain.HealthStatus;
import com.cocktailsguru.app.person.repository.PersonRepository;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Log4j
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

        boolean dbAlive;
        try {
            dbAlive = personRepository.findOne(1) != null;
        } catch (Exception e) {
            dbAlive = false;
            log.error(e);
        }
        status.setDbAlive(dbAlive);

        return status;
    }
}
