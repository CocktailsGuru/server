package com.cocktailsguru.app.health.service;


import com.cocktailsguru.app.health.domain.HealthStatus;
import com.cocktailsguru.app.person.domain.Person;
import com.cocktailsguru.app.person.repository.PersonRepository;
import de.bechte.junit.runners.context.HierarchicalContextRunner;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

@RunWith(HierarchicalContextRunner.class)
public class HealthServiceImplTest {
    @Mock
    private PersonRepository personRepository;

    @InjectMocks
    private HealthServiceImpl healthService;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    public class GivenLiveDb {
        @Mock
        private Person anyPerson;

        @Before
        public void setUp() {
            MockitoAnnotations.initMocks(this);
            when(personRepository.findOne(1)).thenReturn(anyPerson);
        }

        public class WhenCallingCheckHealth {
            @Test
            public void dbShouldBeAlive() {
                HealthStatus status = healthService.checkHealth();
                assertTrue(status.isDbAlive());
            }

            @Test
            public void springShouldBeAlive() {
                HealthStatus status = healthService.checkHealth();
                assertTrue(status.isSpringAlive());
            }
        }
    }

    public class GivenDeadDb {
        public class WhenCallingCheckHealth {
            @Test
            public void dbShouldBeDead() {
                HealthStatus status = healthService.checkHealth();
                assertFalse(status.isDbAlive());
            }

            @Test
            public void springShouldBeAlive() {
                HealthStatus status = healthService.checkHealth();
                assertTrue(status.isSpringAlive());
            }
        }
    }


    public class GivenDbException {
        @Before
        public void setUp() {
            doThrow(new IllegalArgumentException()).when(personRepository).findOne(1);
        }

        public class WhenCallingCheckHealth {
            @Test
            public void dbShouldBeDead() {
                HealthStatus status = healthService.checkHealth();
                assertFalse(status.isDbAlive());
            }

            @Test
            public void springShouldBeAlive() {
                HealthStatus status = healthService.checkHealth();
                assertTrue(status.isSpringAlive());
            }
        }
    }
}