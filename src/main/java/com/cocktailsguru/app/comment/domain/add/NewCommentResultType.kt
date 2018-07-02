package com.cocktailsguru.app.comment.domain.add

enum class NewCommentResultType(val description: String) {
    OBJECT_NOT_FOUND("Specified object was not found"),
    OK("ok")
}