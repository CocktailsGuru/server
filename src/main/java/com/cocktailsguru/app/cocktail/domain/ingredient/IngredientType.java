package com.cocktailsguru.app.cocktail.domain.ingredient;

import lombok.Getter;

import javax.persistence.Entity;

@Entity(name = "coctail_ingred_category")
@Getter
public enum IngredientType {
    JUICE(1, "Juice"),
    SYRUP(2, "Syrup"),
    BERRY_SYRUP(3, "Berry Syrup"),
    PUREE(4, "Puree"),
    SQUASH(5, "Squash");
    private Integer id;

    private String name;

    IngredientType(Integer id, String name) {
        this.id = id;
        this.name = name;
    }
}
