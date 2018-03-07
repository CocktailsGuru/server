package com.cocktailsguru.app.user.domain

data class UserTokenToVerify(
        val userId: Long,
        val token: String
)