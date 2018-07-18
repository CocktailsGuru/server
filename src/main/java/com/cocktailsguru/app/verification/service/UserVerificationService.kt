package com.cocktailsguru.app.verification.service

import com.cocktailsguru.app.user.domain.User

interface UserVerificationService {
    companion object {
        val USER_ID_HEADER: String
            get() = "userId"
        val TOKEN_HEADER: String
            get() = "token"
    }

    fun verifyUser(): Boolean

    fun getLoggedUser(): User
}