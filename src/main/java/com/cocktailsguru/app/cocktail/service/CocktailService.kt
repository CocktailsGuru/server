package com.cocktailsguru.app.cocktail.service

import com.cocktailsguru.app.cocktail.domain.Cocktail
import com.cocktailsguru.app.cocktail.domain.CocktailList
import com.cocktailsguru.app.common.domain.PagingInfo

interface CocktailService {
    fun getCocktailDetail(id: Long): Cocktail?

    fun getCocktailList(listRequest: PagingInfo): CocktailList
}