package com.cocktailsguru.app.ingredient.service

import com.cocktailsguru.app.comment.domain.CommentList
import com.cocktailsguru.app.comment.domain.add.NewCommentRequest
import com.cocktailsguru.app.comment.domain.add.NewCommentResult
import com.cocktailsguru.app.common.domain.ObjectDetailRequest
import com.cocktailsguru.app.common.domain.PagingInfo
import com.cocktailsguru.app.ingredient.domain.Ingredient
import com.cocktailsguru.app.ingredient.domain.IngredientDetail
import com.cocktailsguru.app.ingredient.domain.IngredientList
import com.cocktailsguru.app.ingredient.domain.IngredientType

interface IngredientService {
    fun getIngredientList(ingredientType: IngredientType?, listRequest: PagingInfo): IngredientList

    fun findIngredientDetail(detailRequest: ObjectDetailRequest): IngredientDetail?

    fun findIngredient(id: Long): Ingredient?

    fun getCommentList(id: Long, commentRequest: PagingInfo): CommentList

    fun addNewComment(ingredientId: Long, commentRequest: NewCommentRequest): NewCommentResult
}