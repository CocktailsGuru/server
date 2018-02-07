package com.cocktailsguru.app.user.domain


data class UserRegistrationRequest(
        val externalUserId: String,
        val name: String,
        val gender: Gender,
        val registrationType: UserRegistrationType,
        val image: String,
        val countryCode: String,
        val language: String?
)