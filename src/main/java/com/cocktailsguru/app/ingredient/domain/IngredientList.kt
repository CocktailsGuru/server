package com.cocktailsguru.app.ingredient.domain

import com.cocktailsguru.app.common.domain.ObjectList
import com.cocktailsguru.app.common.domain.PagingInfo

data class IngredientList(
        override val objectList: List<Ingredient>,
        override val pagingInfo: PagingInfo
) : ObjectList<Ingredient>