package com.cocktailsguru.app.cocktail.dto.detail

import com.cocktailsguru.app.comment.dto.CommentListResponseDto
import com.cocktailsguru.app.common.dto.ObjectDetailResponseDto
import com.cocktailsguru.app.picture.dto.PictureListResponseDto
import com.fasterxml.jackson.annotation.JsonProperty

data class CocktailDetailResponseDto(
        @JsonProperty(value = "cocktail")
        override val objectDetail: CocktailDetailDto,
        override val commentsDtoList: CommentListResponseDto,
        val pictureDtoList: PictureListResponseDto
) : ObjectDetailResponseDto<CocktailDetailDto>