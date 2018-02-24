package com.cocktailsguru.app.user.domain.registration

import com.cocktailsguru.app.user.domain.User

data class UserRegistrationResult(
        val user: User,
        val registrationResultType: UserRegistrationResultType
)