package com.cocktailsguru.app.user.dto.favorite

import com.cocktailsguru.app.user.domain.favorite.SetFavoriteResultType


data class SetFavoriteResponseDto(
        val resultType: SetFavoriteResultType
)
