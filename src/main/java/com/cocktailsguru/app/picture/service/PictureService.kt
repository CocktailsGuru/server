package com.cocktailsguru.app.picture.service

import com.cocktailsguru.app.cocktail.domain.Cocktail
import com.cocktailsguru.app.cocktail.domain.CocktailObjectType
import com.cocktailsguru.app.comment.domain.add.NewCommentRequest
import com.cocktailsguru.app.comment.domain.add.NewCommentResult
import com.cocktailsguru.app.common.domain.PagingInfo
import com.cocktailsguru.app.picture.domain.PictureList

interface PictureService {
    fun getPictureList(cocktail: Cocktail, pagingInfo: PagingInfo): PictureList

    fun getPictureListForObject(objectType: CocktailObjectType, id: Long, pagingInfo: PagingInfo): PictureList

    fun addNewComment(pictureId: Long, commentRequest: NewCommentRequest): NewCommentResult
}