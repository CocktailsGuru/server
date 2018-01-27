package com.cocktailsguru.app.ingredient.dto.list

import com.cocktailsguru.app.common.dto.PagingDto

data class AlcoIngredientListResponseDto(
        val alcoIngredientList: List<AlcoIngredientListItemDto>,
        val pagingInfo: PagingDto
)