package com.cocktailsguru.app.comment.domain.add

import com.cocktailsguru.app.user.domain.User

data class NewCommentRequest(
        val content: String,
        val authorUser: User
)
