package com.cocktailsguru.app.ingredient.domain

import com.cocktailsguru.app.cocktail.domain.Cocktail
import javax.persistence.*

@Entity
@Table(name = "coctail_ingredients")
data class IngredientCocktail(
        @EmbeddedId
        val id: IngredientCocktailId,

        @ManyToOne
        @MapsId("cocktailId")
        @JoinColumn(name = "coctail_id")
        val cocktail: Cocktail,

        @ManyToOne
        @MapsId("ingredientId")
        @JoinColumn(name = "ingredient_id")
        val ingredient: Ingredient,

        val volume: Int
) {
    override fun toString(): String {
        return String.format("IngredientCocktail(cocktailId %s, ingredientId %s, volume %s)", cocktail.id, ingredient.id, volume)
    }
}