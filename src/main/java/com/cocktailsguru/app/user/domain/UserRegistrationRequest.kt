package com.cocktailsguru.app.user.domain


data class UserRegistrationRequest(
        val externalUserId: String,
        var name: String,
        val gender: Gender,
        val registrationType: UserRegistrationType,
        var image: String,
        var countryCode: String,
        val language: String
)