package com.cocktailsguru.app.ingredient.dto.list

import com.cocktailsguru.app.common.dto.PagingDto

data class NonAlcoIngredientListResponseDto(
        val nonAlcoIngredientList: List<NonAlcoIngredientListItemDto>,
        val pagingInfo: PagingDto
)