package com.cocktailsguru.app.comment.dto

data class NewCommentRequestDto(
        val authorUserId: Long,
        val objectType: Int,
        val objectForeignKey: Long,
        val content: String
)
