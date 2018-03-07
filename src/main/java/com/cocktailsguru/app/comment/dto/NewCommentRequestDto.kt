package com.cocktailsguru.app.comment.dto

import com.cocktailsguru.app.comment.domain.add.NewCommentRequest
import com.cocktailsguru.app.user.dto.UserTokenDto

data class NewCommentRequestDto(
        val userTokenDto: UserTokenDto,
        val objectId: Long,
        val content: String
) {
    fun toNewCommentRequest() = NewCommentRequest(userTokenDto.toUserTokenToVerify(), content)
}
