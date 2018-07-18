package com.cocktailsguru.app.user.domain.favorite

enum class SetFavoriteResultType(val description: String) {
    OBJECT_NOT_FOUND("Specified object was not found"),
    ALREADY_FAVORITE("Specified object is already favorite"),
    OK("ok")
}