package com.cocktailsguru.app.person.repository;

import com.cocktailsguru.app.person.domain.Person;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends CrudRepository<Person, Integer> {
}
