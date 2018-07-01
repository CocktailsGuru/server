package com.cocktailsguru.app.user.domain.rating

data class RateObjectRequest(
        val objectId: Long,
        val rating: RatingType
)