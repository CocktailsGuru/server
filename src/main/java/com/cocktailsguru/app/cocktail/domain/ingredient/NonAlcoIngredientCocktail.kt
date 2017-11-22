package com.cocktailsguru.app.cocktail.domain.ingredient

import com.cocktailsguru.app.cocktail.domain.Cocktail
import javax.persistence.*

@Entity
@Table(name = "coctail_non_alco_ingredients")
data class NonAlcoIngredientCocktail(
        @EmbeddedId
        val id: NonAlcoIngredientCocktailId,

        @ManyToOne
        @MapsId("cocktailId")
        @JoinColumn(name = "coctail_id")
        val cocktail: Cocktail,

        @ManyToOne
        @MapsId("ingredientId")
        @JoinColumn(name = "ingred_non_alco_id")
        val ingredient: NonAlcoIngredient,

        val volume: Int
) {
    override fun toString(): String {
        return String.format("NonAlcoIngredientCocktail(cocktailId %s, ingredientId %s, volume %s)", cocktail.id, ingredient.id, volume)
    }
}