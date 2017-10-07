package com.cocktailsguru.app.person.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
public class Person {

    @Id
    private Integer id;
    private String name;
}
