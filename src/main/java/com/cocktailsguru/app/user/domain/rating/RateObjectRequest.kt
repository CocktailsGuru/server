package com.cocktailsguru.app.user.domain.rating

import com.cocktailsguru.app.user.domain.UserTokenToVerify

data class RateObjectRequest(
        val userToken: UserTokenToVerify,
        val objectId: Long,
        val rating: RatingType
)