package com.cocktailsguru.app.comment.dto

import com.cocktailsguru.app.comment.domain.Comment
import com.cocktailsguru.app.user.dto.UserInfoResponseDto
import java.time.LocalDateTime

data class CommentResponseDto(
        val id: Long,
        val objectForeignKey: Long,
        val objectName: String,
        val authorUser: UserInfoResponseDto,
        val content: String,
        val numLikes: Int,
        val numDislikes: Int,
        val visible: Boolean,
        val updateTime: LocalDateTime,
        val createdTime: LocalDateTime
) {
    constructor(comment: Comment) : this(
            comment.id,
            comment.objectForeignKey,
            comment.objectName,
            UserInfoResponseDto(comment.authorUser),
            comment.content,
            comment.numLikes,
            comment.numDislikes,
            comment.isVisible,
            comment.updateTime,
            comment.createdTime
    )
}

