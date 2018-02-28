package com.cocktailsguru.app.picture.dto

import com.cocktailsguru.app.common.dto.ListResponseDto
import com.cocktailsguru.app.common.dto.PagingDto
import com.cocktailsguru.app.picture.domain.PictureList
import com.fasterxml.jackson.annotation.JsonProperty

data class PictureListResponseDto(
        @JsonProperty(value = "pictureList")
        override val list: List<PictureResponseDto>,
        override val pagingInfo: PagingDto
) : ListResponseDto<PictureResponseDto> {
    constructor(pictureList: PictureList) : this(
            pictureList.objectList.map { PictureResponseDto(it) },
            PagingDto(pictureList.pagingInfo)
    )
}