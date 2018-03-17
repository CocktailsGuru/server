package com.cocktailsguru.app.comment.service

import com.cocktailsguru.app.cocktail.domain.Cocktail
import com.cocktailsguru.app.cocktail.domain.CocktailObjectType
import com.cocktailsguru.app.comment.domain.CommentList
import com.cocktailsguru.app.comment.domain.add.NewCommentRequest
import com.cocktailsguru.app.comment.domain.add.NewCommentResult
import com.cocktailsguru.app.common.domain.PagingInfo
import com.cocktailsguru.app.ingredient.domain.Ingredient
import com.cocktailsguru.app.picture.domain.Picture

interface CommentService {
    fun getCommentList(cocktail: Cocktail, pagingInfo: PagingInfo): CommentList

    fun getCommentList(ingredient: Ingredient, pagingInfo: PagingInfo): CommentList

    fun getCommentList(picture: Picture, pagingInfo: PagingInfo): CommentList

    fun getCommentListForObject(objectType: CocktailObjectType, id: Long, pagingInfo: PagingInfo): CommentList

    fun addNewComment(cocktail: Cocktail?, commentRequest: NewCommentRequest): NewCommentResult

    fun addNewComment(ingredient: Ingredient?, commentRequest: NewCommentRequest): NewCommentResult

    fun addNewComment(picture: Picture?, commentRequest: NewCommentRequest): NewCommentResult
}
