package com.cocktailsguru.app.user.service

import com.cocktailsguru.app.user.domain.FbUser
import com.cocktailsguru.app.user.domain.GoogleUser
import com.cocktailsguru.app.user.domain.User
import com.cocktailsguru.app.user.domain.UserRegistrationRequest
import com.cocktailsguru.app.user.domain.registration.UserRegistrationResult

interface UserService {
    fun findUserById(id: Long): User?

    fun findFbUserById(id: Long): FbUser?

    fun findGoogleUserById(id: Long): GoogleUser?

    fun registerUser(registrationRequest: UserRegistrationRequest): UserRegistrationResult
}