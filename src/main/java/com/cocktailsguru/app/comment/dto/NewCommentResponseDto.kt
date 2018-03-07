package com.cocktailsguru.app.comment.dto

import com.cocktailsguru.app.comment.domain.add.NewCommentResult
import com.cocktailsguru.app.comment.domain.add.NewCommentResultType

data class NewCommentResponseDto(
        val resultType: NewCommentResultType
) {
    constructor(newCommentResult: NewCommentResult) : this(newCommentResult.resultType)
}