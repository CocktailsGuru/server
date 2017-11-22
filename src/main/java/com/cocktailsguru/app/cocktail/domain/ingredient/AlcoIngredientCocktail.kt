package com.cocktailsguru.app.cocktail.domain.ingredient

import com.cocktailsguru.app.cocktail.domain.Cocktail
import javax.persistence.*

@Entity
@Table(name = "coctail_alco_ingredients")
data class AlcoIngredientCocktail(
        @EmbeddedId
        val id: AlcoIngredientCocktailId,

        @ManyToOne
        @MapsId("cocktailId")
        @JoinColumn(name = "coctail_id")
        val cocktail: Cocktail,

        @ManyToOne
        @MapsId("ingredientId")
        @JoinColumn(name = "ingred_alco_id")
        val ingredient: AlcoIngredient,

        val volume: Int
) {
    override fun toString(): String {
        return String.format("AlcoIngredientCocktail(cocktailId %s, ingredientId %s, volume %s)", cocktail.id, ingredient.id, volume)
    }
}