package com.cocktailsguru.app.user.dto

import com.cocktailsguru.app.user.domain.User

data class UserInfoResponseDto(
        val id: Long,
        val name: String,
        val image: String
) {
    constructor(user: User) : this(user.id, user.name, user.image)
}


