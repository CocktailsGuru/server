package com.cocktailsguru.app.cocktail.service

import com.cocktailsguru.app.cocktail.domain.Cocktail
import com.cocktailsguru.app.cocktail.domain.CocktailList
import com.cocktailsguru.app.cocktail.domain.detail.CocktailDetail
import com.cocktailsguru.app.cocktail.domain.detail.CocktailDetailRequest
import com.cocktailsguru.app.comment.domain.CommentList
import com.cocktailsguru.app.comment.domain.add.NewCommentRequest
import com.cocktailsguru.app.comment.domain.add.NewCommentResult
import com.cocktailsguru.app.common.domain.PagingInfo

interface CocktailService {
    fun findCocktailDetail(request: CocktailDetailRequest): CocktailDetail?

    fun findCocktail(id: Long): Cocktail?

    fun getCocktailList(listRequest: PagingInfo): CocktailList

    fun getCommentList(id: Long, commentRequest: PagingInfo): CommentList

    fun addNewComment(cocktailId: Long, commentRequest: NewCommentRequest): NewCommentResult
}