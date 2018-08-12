package com.cocktailsguru.app.user.dto.favorite

import com.cocktailsguru.app.cocktail.domain.CocktailObjectType
import com.cocktailsguru.app.user.domain.favorite.UserFavorite

data class FavoriteDto(
        val id: Long,
        val objectType: CocktailObjectType,
        val objectForeignKey: Long,
        val authorUserId: Long
) {
    constructor(favorite: UserFavorite) : this(
            favorite.id,
            favorite.objectType,
            favorite.objectForeignKey,
            favorite.user.id
    )
}