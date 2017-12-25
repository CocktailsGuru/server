package com.cocktailsguru.app.cocktail.service

import com.cocktailsguru.app.cocktail.domain.Cocktail

interface CocktailService {
    fun getCocktailDetail(id: Long): Cocktail?
}