package com.cocktailsguru.app.user.domain.favorite

enum class SetFavoriteResultType(val description: String) {
    USER_NOT_FOUND("Specified user was not found"),
    OBJECT_NOT_FOUND("Specified object was not found"),
    ALREADY_FAVORITE("Specified object is already favorite"),
    OK("ok")
}