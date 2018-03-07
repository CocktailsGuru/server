package com.cocktailsguru.app.user.dto.registration

import com.cocktailsguru.app.user.domain.registration.UserRegistrationResult
import com.cocktailsguru.app.user.domain.registration.UserRegistrationResultType

data class RegisterUserResponseDto(
        val id: Long,
        val token: String,
        val userRegistrationResultType: UserRegistrationResultType
) {
    constructor(registrationResult: UserRegistrationResult) : this(
            registrationResult.user.id,
            registrationResult.token.token,
            registrationResult.registrationResultType
    )
}