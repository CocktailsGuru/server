package com.cocktailsguru.app.comment.dto

import com.cocktailsguru.app.comment.domain.CommentList
import com.cocktailsguru.app.common.dto.ListResponseDto
import com.cocktailsguru.app.common.dto.PagingDto
import com.fasterxml.jackson.annotation.JsonProperty

data class CommentListResponseDto(
        @JsonProperty(value = "commentList")
        override val list: List<CommentResponseDto>,
        override val pagingInfo: PagingDto
) : ListResponseDto<CommentResponseDto> {
    constructor(commentList: CommentList) : this(
            commentList.objectList.map { CommentResponseDto(it) },
            PagingDto(commentList.pagingInfo)
    )
}