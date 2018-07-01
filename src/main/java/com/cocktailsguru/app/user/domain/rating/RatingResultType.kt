package com.cocktailsguru.app.user.domain.rating

enum class RatingResultType(val description: String) {
    OBJECT_NOT_FOUND("Specified object was not found"),
    ALREADY_RATED("Specified object is already rated"),
    OK("ok")
}