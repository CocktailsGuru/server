package com.cocktailsguru.app.cocktail.domain.ingredient;

import lombok.Data;

import javax.persistence.*;

@MappedSuperclass
@Data
abstract class BaseIngredient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String nameGrouped;

    @Column(name = "desc")
    private String description;

    @Column(name = "imgFileName")
    private String imageName;

    private Integer numShowed;

    @OneToOne
    @JoinColumn(name = "categoryFK")
    private IngredientType ingredientType;


}
