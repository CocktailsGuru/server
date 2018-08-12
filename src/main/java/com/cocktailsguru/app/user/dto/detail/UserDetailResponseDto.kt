package com.cocktailsguru.app.user.dto.detail

import com.cocktailsguru.app.user.domain.detail.UserDetail
import com.cocktailsguru.app.user.dto.favorite.FavoriteDto
import com.cocktailsguru.app.user.dto.rating.RatingDto

data class UserDetailResponseDto(
        val ratedObjectsList: List<RatingDto>,
        val favoriteObjectsList: List<FavoriteDto>
) {
    constructor(userDetail: UserDetail) : this(
            userDetail.ratingList.map { RatingDto(it) },
            userDetail.favoriteList.map { FavoriteDto(it) }
    )
}