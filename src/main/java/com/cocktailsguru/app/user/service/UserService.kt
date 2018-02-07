package com.cocktailsguru.app.user.service

import com.cocktailsguru.app.user.domain.FbUser
import com.cocktailsguru.app.user.domain.GoogleUser
import com.cocktailsguru.app.user.domain.User

interface UserService {
    fun findUserById(id: Long): User?

    fun findFbUserById(id: Long): FbUser?

    fun findGoogleUserById(id: Long): GoogleUser?
}