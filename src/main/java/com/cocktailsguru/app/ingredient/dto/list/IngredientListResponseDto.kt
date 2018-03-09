package com.cocktailsguru.app.ingredient.dto.list

import com.cocktailsguru.app.common.dto.ListResponseDto
import com.cocktailsguru.app.common.dto.PagingDto

data class IngredientListResponseDto constructor(
        override val list: List<IngredientListItemDto>,
        override val pagingInfo: PagingDto
) : ListResponseDto<IngredientListItemDto>
