package com.cocktailsguru.app.comment.domain.add

data class NewCommentRequest(
        val content: String,
        val author: User
)
