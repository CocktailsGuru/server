package com.cocktailsguru.app.picture.dto

import com.cocktailsguru.app.picture.domain.Picture
import com.cocktailsguru.app.user.dto.UserInfoResponseDto
import java.time.LocalDateTime

data class PictureResponseDto(
        val id: Long,
        val objectForeignKey: Long,
        val authorUser: UserInfoResponseDto,
        val fileName: String,
        val objectName: String,
        val description: String,
        val numLikes: Int,
        val numDislikes: Int,
        val numFav: Int,
        val numComments: Int,
        val numShowed: Int,
        val updateTime: LocalDateTime,
        val createdTime: LocalDateTime
) {
    constructor(picture: Picture) : this(
            picture.id,
            picture.objectForeignKey,
            UserInfoResponseDto(picture.authorUser),
            picture.fileName,
            picture.objectName,
            picture.description,
            picture.numLikes,
            picture.numDisLikes,
            picture.numFavorite,
            picture.numComments,
            picture.numShowed,
            picture.updateTime,
            picture.createdTime
    )
}

