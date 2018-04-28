package com.cocktailsguru.app.user.domain.detail

import com.cocktailsguru.app.user.domain.favorite.UserFavorite
import com.cocktailsguru.app.user.domain.rating.UserRating

data class UserDetail(
        val favoriteList: List<UserFavorite>,
        val ratingList: List<UserRating>
)