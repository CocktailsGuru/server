package com.cocktailsguru.app.ingredient.dto.list

import com.cocktailsguru.app.ingredient.domain.Ingredient

data class IngredientListItemDto(
        val id: Long,
        val imageName: String,
        val nameGrouped: String,
        val voltage: String
) {
    constructor(ingredient: Ingredient) : this(
            ingredient.id,
            ingredient.imageName,
            ingredient.nameGrouped,
            ingredient.voltage
    )
}