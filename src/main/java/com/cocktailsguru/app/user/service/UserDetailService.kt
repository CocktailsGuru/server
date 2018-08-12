package com.cocktailsguru.app.user.service

import com.cocktailsguru.app.user.domain.User
import com.cocktailsguru.app.user.domain.detail.UserDetail

interface UserDetailService {
    fun getUserDetail(user: User): UserDetail
}