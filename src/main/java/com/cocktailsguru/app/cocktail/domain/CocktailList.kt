package com.cocktailsguru.app.cocktail.domain

import com.cocktailsguru.app.common.domain.PagingInfo

data class CocktailList(
        val list: List<Cocktail>,
        val pagingInfo: PagingInfo
)