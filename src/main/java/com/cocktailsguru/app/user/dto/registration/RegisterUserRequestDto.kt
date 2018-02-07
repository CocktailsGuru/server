package com.cocktailsguru.app.user.dto.registration

data class RegisterUserRequestDto(
        val externalUserId: String,
        val name: String,
        val gender: String,
        val registrationType: String,
        val image: String,
        val countryCode: String,
        val language: String?
)