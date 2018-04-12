package com.cocktailsguru.app.user.service

import com.cocktailsguru.app.user.domain.User
import com.cocktailsguru.app.user.domain.rating.RateObjectRequest
import com.cocktailsguru.app.user.domain.rating.RatingResultType
import com.cocktailsguru.app.user.domain.rating.UserRating

interface UserRatingService {
    fun rateCocktail(ratingRequest: RateObjectRequest): RatingResultType

    fun getRatingsOfUser(user: User): List<UserRating>
}
