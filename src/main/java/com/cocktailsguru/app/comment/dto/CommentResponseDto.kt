package com.cocktailsguru.app.comment.dto

import com.cocktailsguru.app.comment.domain.Comment
import java.time.LocalDateTime

data class CommentResponseDto(
        val id: Long,
        val objectForeignKey: Long,
        val authorUserId: Long,
        val content: String,
        val numLikes: Int,
        val numDislikes: Int,
        val isVisible: Boolean,
        val updateTime: LocalDateTime,
        val createdTime: LocalDateTime
) {
    constructor(comment: Comment) : this(
            comment.id,
            comment.objectForeignKey,
            comment.authorUser.id,
            comment.content,
            comment.numLikes,
            comment.numDislikes,
            comment.isVisible,
            comment.updateTime,
            comment.createdTime
    )
}

