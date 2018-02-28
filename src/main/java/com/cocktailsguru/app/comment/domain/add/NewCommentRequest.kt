package com.cocktailsguru.app.comment.domain.add

import com.cocktailsguru.app.cocktail.domain.CocktailObjectType

data class NewCommentRequest(
        val authorUserId: Long,
        val objectType: CocktailObjectType,
        val objectForeignKey: Long,
        val content: String
)
