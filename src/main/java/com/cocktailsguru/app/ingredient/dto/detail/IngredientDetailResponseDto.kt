package com.cocktailsguru.app.ingredient.dto.detail

import com.cocktailsguru.app.comment.dto.CommentListResponseDto
import com.cocktailsguru.app.common.dto.ObjectDetailResponseDto
import com.fasterxml.jackson.annotation.JsonProperty

data class IngredientDetailResponseDto(
        @JsonProperty(value = "ingredient")
        override val objectDetail: IngredientDetailDto,
        override val commentsDtoList: CommentListResponseDto
) : ObjectDetailResponseDto<IngredientDetailDto>