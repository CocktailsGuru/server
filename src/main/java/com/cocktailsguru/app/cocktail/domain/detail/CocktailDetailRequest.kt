package com.cocktailsguru.app.cocktail.domain.detail

import com.cocktailsguru.app.common.domain.ObjectDetailRequest

data class CocktailDetailRequest(
        val detailRequest: ObjectDetailRequest,
        val picturesSize: Int
)