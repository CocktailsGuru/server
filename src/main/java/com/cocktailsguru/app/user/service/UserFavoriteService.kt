package com.cocktailsguru.app.user.service

import com.cocktailsguru.app.cocktail.domain.CocktailObjectType
import com.cocktailsguru.app.user.domain.User
import com.cocktailsguru.app.user.domain.favorite.SetFavoriteResultType
import com.cocktailsguru.app.user.domain.favorite.UserFavorite

interface UserFavoriteService {
    fun getFavoriteObjects(objectType: CocktailObjectType, id: Long): List<UserFavorite>

    fun getCountOfFavoriteObjects(objectType: CocktailObjectType, id: Long): Long

    fun setCocktailAsFavorite(cocktailId: Long, user: User): SetFavoriteResultType

    fun setPictureAsFavorite(pictureId: Long, user: User): SetFavoriteResultType

    fun getFavoritesOfUser(user: User): List<UserFavorite>
}