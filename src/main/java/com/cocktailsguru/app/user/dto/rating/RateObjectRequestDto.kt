package com.cocktailsguru.app.user.dto.rating

import com.cocktailsguru.app.user.domain.rating.RateObjectRequest
import com.cocktailsguru.app.user.domain.rating.RatingType
import com.cocktailsguru.app.user.dto.UserTokenDto

data class RateObjectRequestDto(
        val userTokenDto: UserTokenDto,
        val objectId: Long,
        val rating: RatingType
) {
    fun toRateObjectRequest() = RateObjectRequest(
            userTokenDto.toUserTokenToVerify(),
            objectId,
            rating
    )
}
