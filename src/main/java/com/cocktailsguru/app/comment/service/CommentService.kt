package com.cocktailsguru.app.comment.service

import com.cocktailsguru.app.cocktail.domain.CocktailObjectType
import com.cocktailsguru.app.comment.domain.Comment

interface CommentService {
    fun getCommentListForObject(objectType: CocktailObjectType, id: Long): List<Comment>
}
