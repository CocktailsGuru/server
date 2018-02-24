package com.cocktailsguru.app.user.dto.registration

import com.cocktailsguru.app.user.domain.registration.UserRegistrationResultType

data class RegisterUserResponseDto(
        val id: Long,
        val userRegistrationResultType: UserRegistrationResultType
)