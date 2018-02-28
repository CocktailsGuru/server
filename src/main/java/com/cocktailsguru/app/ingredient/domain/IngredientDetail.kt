package com.cocktailsguru.app.ingredient.domain

import com.cocktailsguru.app.comment.domain.CommentList
import com.cocktailsguru.app.common.domain.ObjectDetail

data class IngredientDetail(
        override val detail: Ingredient,
        override val commentList: CommentList
) : ObjectDetail<Ingredient>