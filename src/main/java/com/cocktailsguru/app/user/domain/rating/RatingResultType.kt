package com.cocktailsguru.app.user.domain.rating

enum class RatingResultType(val description: String) {
    USER_NOT_FOUND("Specified user was not found"),
    OBJECT_NOT_FOUND("Specified object was not found"),
    ALREADY_RATED("Specified object is already rated"),
    OK("ok")
}