package com.cocktailsguru.app.cocktail.domain

import com.cocktailsguru.app.common.domain.ObjectList
import com.cocktailsguru.app.common.domain.PagingInfo

data class CocktailList(
        override val objectList: List<Cocktail>,
        override val pagingInfo: PagingInfo
) : ObjectList<Cocktail>