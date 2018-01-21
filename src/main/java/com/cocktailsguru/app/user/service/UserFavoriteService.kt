package com.cocktailsguru.app.user.service

import com.cocktailsguru.app.cocktail.domain.CocktailObjectType
import com.cocktailsguru.app.user.domain.UserFavorite

interface UserFavoriteService {
    fun getFavoriteObjects(objectType: CocktailObjectType, id: Long): List<UserFavorite>
}