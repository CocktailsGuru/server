package com.cocktailsguru.app.user.dto.rating

import com.cocktailsguru.app.user.domain.rating.RateObjectRequest
import com.cocktailsguru.app.user.domain.rating.RatingType

data class RateObjectRequestDto(
        val objectId: Long,
        val rating: RatingType
) {
    fun toRateObjectRequest() = RateObjectRequest(
            objectId,
            rating
    )
}
