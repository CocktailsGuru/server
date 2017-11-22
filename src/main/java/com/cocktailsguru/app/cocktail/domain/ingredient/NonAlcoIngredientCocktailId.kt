package com.cocktailsguru.app.cocktail.domain.ingredient

import java.io.Serializable
import javax.persistence.Column
import javax.persistence.Embeddable

@Embeddable
data class NonAlcoIngredientCocktailId(
        @Column(name = "coctail_id")
        val cocktailId: Long,
        @Column(name = "ingred_non_alco_id")
        val ingredientId: Long
) : Serializable