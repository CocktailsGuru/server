package com.cocktailsguru.app.user.domain.registration

import com.cocktailsguru.app.user.domain.User
import com.cocktailsguru.app.user.domain.UserToken

data class UserRegistrationResult(
        val user: User,
        val token: UserToken,
        val registrationResultType: UserRegistrationResultType
)