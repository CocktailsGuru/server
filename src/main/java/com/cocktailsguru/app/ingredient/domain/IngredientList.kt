package com.cocktailsguru.app.ingredient.domain

import com.cocktailsguru.app.common.domain.PagingInfo

data class IngredientList<out T : BaseIngredient>(
        val list: List<T>,
        val pagingInfo: PagingInfo
)