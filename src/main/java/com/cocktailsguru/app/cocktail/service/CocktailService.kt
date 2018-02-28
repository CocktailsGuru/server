package com.cocktailsguru.app.cocktail.service

import com.cocktailsguru.app.cocktail.domain.Cocktail
import com.cocktailsguru.app.cocktail.domain.CocktailList
import com.cocktailsguru.app.cocktail.domain.detail.CocktailDetail
import com.cocktailsguru.app.cocktail.domain.detail.CocktailDetailRequest
import com.cocktailsguru.app.common.domain.PagingInfo

interface CocktailService {
    fun findCocktailDetail(request: CocktailDetailRequest): CocktailDetail?

    fun findCocktail(id: Long): Cocktail?

    fun getCocktailList(listRequest: PagingInfo): CocktailList
}