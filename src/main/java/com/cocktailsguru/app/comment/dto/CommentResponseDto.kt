package com.cocktailsguru.app.comment.dto

import java.time.LocalDateTime

data class CommentResponseDto(
        val id: Long,
        val userId: Long,
        val content: String,
        var numLikes: Int,
        var numDislikes: Int,
        var isVisible: Boolean,
        var updateTime: LocalDateTime
)
