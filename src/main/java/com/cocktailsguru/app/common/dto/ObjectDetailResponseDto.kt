package com.cocktailsguru.app.common.dto

import com.cocktailsguru.app.comment.dto.CommentListResponseDto

interface ObjectDetailResponseDto<out T> {
    val objectDetail: T
    val commentsDtoList: CommentListResponseDto
}