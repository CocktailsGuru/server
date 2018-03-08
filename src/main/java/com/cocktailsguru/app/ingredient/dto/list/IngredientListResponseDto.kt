package com.cocktailsguru.app.ingredient.dto.list

import com.cocktailsguru.app.common.dto.ListResponseDto
import com.cocktailsguru.app.common.dto.PagingDto
import com.fasterxml.jackson.annotation.JsonProperty

data class IngredientListResponseDto constructor(
        @JsonProperty(value = "list")
        override val list: List<IngredientListItemDto>,
        override val pagingInfo: PagingDto
) : ListResponseDto<IngredientListItemDto>
