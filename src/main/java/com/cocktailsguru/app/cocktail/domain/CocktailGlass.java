package com.cocktailsguru.app.cocktail.domain;

import lombok.Data;

import javax.persistence.*;

@Entity(name = "coctail_glass")
@Data
public class CocktailGlass {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;
}
