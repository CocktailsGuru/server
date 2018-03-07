package com.cocktailsguru.app.comment.domain.add

import com.cocktailsguru.app.user.domain.UserTokenToVerify

data class NewCommentRequest(
        val authorToken: UserTokenToVerify,
        val content: String
)
