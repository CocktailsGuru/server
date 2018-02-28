package com.cocktailsguru.app.ingredient.service

import com.cocktailsguru.app.common.domain.ObjectDetailRequest
import com.cocktailsguru.app.common.domain.PagingInfo
import com.cocktailsguru.app.ingredient.domain.Ingredient
import com.cocktailsguru.app.ingredient.domain.IngredientDetail
import com.cocktailsguru.app.ingredient.domain.IngredientList

interface IngredientService {
    fun getIngredientList(listRequest: PagingInfo): IngredientList

    fun findIngredientDetail(detailRequest: ObjectDetailRequest): IngredientDetail?

    fun findIngredient(id: Long): Ingredient?
}