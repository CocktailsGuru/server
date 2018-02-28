package com.cocktailsguru.app.comment.domain.add

enum class NewCommentResultType(val description: String) {
    USER_NOT_FOUND("Specified user was not found"),
    OBJECT_NOT_FOUND("Specified object was not found"),
    OK("ok")
}