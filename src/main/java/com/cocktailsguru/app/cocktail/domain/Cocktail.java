package com.cocktailsguru.app.cocktail.domain;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity(name = "coctail")
public class Cocktail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

}
