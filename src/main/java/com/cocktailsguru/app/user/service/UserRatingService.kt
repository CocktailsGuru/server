package com.cocktailsguru.app.user.service

import com.cocktailsguru.app.user.domain.User
import com.cocktailsguru.app.user.domain.rating.RateObjectRequest
import com.cocktailsguru.app.user.domain.rating.RatingResultType

interface UserRatingService {
    fun rateCocktail(ratingRequest: RateObjectRequest, user: User): RatingResultType
}
