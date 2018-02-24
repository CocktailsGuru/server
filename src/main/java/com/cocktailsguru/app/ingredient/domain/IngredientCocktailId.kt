package com.cocktailsguru.app.ingredient.domain

import java.io.Serializable
import javax.persistence.Column
import javax.persistence.Embeddable

@Embeddable
data class IngredientCocktailId(
        @Column(name = "coctail_id")
        val cocktailId: Long,
        @Column(name = "ingredient_id")
        val ingredientId: Long
) : Serializable