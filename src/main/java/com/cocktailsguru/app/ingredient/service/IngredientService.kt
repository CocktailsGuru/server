package com.cocktailsguru.app.ingredient.service

import com.cocktailsguru.app.common.domain.PagingInfo
import com.cocktailsguru.app.ingredient.domain.Ingredient
import com.cocktailsguru.app.ingredient.domain.IngredientList

interface IngredientService {
    fun getIngredientList(listRequest: PagingInfo): IngredientList

    fun findIngredient(id: Long): Ingredient?
}