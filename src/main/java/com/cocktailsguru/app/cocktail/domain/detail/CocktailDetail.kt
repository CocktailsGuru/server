package com.cocktailsguru.app.cocktail.domain.detail

import com.cocktailsguru.app.cocktail.domain.Cocktail
import com.cocktailsguru.app.comment.domain.CommentList
import com.cocktailsguru.app.common.domain.ObjectDetail
import com.cocktailsguru.app.picture.domain.PictureList

data class CocktailDetail(
        override val detail: Cocktail,
        override val commentList: CommentList,
        val pictureList: PictureList
) : ObjectDetail<Cocktail>