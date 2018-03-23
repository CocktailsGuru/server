package com.cocktailsguru.app.user.dto.favorite

import com.cocktailsguru.app.user.dto.UserTokenDto

data class SetFavoriteRequestDto(
        val userTokenDto: UserTokenDto,
        val objectId: Long
)
