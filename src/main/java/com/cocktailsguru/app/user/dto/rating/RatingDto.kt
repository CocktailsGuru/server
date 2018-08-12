package com.cocktailsguru.app.user.dto.rating

import com.cocktailsguru.app.cocktail.domain.CocktailObjectType
import com.cocktailsguru.app.user.domain.rating.RatingType
import com.cocktailsguru.app.user.domain.rating.UserRating
import java.time.LocalDateTime

data class RatingDto(
        val id: Long,
        val objectType: CocktailObjectType,
        val objectForeignKey: Long,
        val authorUserId: Long,
        val rating: RatingType,
        val createdTime: LocalDateTime
) {
    constructor(rating: UserRating) : this(
            rating.id,
            rating.objectType,
            rating.objectForeignKey,
            rating.user.id,
            rating.rating,
            rating.createdTime
    )
}