package com.cocktailsguru.app.ingredient.domain

import com.cocktailsguru.app.common.domain.PagingInfo

data class IngredientList(
        val list: List<Ingredient>,
        val pagingInfo: PagingInfo
)