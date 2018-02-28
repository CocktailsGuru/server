package com.cocktailsguru.app.comment.domain.add

import com.cocktailsguru.app.comment.domain.Comment

data class NewCommentResult(
        val resultType: NewCommentResultType,
        val comment: Comment?
)