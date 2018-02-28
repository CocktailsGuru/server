package com.cocktailsguru.app.ingredient.dto.detail

import com.cocktailsguru.app.ingredient.domain.Ingredient
import com.cocktailsguru.app.ingredient.domain.IngredientCategoryType
import com.cocktailsguru.app.ingredient.domain.IngredientType

data class IngredientDetailDto(
        val id: Long,
        var name: String,
        var nameGrouped: String,
        var voltage: String,
        var description: String,
        var imageName: String,
        var numShowed: Int,
        var ingredientCategoryType: IngredientCategoryType,
        var ingredientType: IngredientType
) {
    constructor(ingredient: Ingredient) : this(
            ingredient.id,
            ingredient.name,
            ingredient.nameGrouped,
            ingredient.voltage,
            ingredient.description,
            ingredient.imageName,
            ingredient.numShowed,
            ingredient.ingredientCategoryType,
            ingredient.ingredientType
    )
}

