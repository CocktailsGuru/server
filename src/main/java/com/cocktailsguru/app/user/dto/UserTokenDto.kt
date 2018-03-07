package com.cocktailsguru.app.user.dto

import com.cocktailsguru.app.user.domain.UserTokenToVerify

data class UserTokenDto(
        val userId: Long,
        val token: String
) {
    fun toUserTokenToVerify() = UserTokenToVerify(userId, token)
}