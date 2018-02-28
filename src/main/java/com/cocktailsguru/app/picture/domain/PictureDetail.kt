package com.cocktailsguru.app.picture.domain

import com.cocktailsguru.app.comment.domain.CommentList
import com.cocktailsguru.app.common.domain.ObjectDetail

data class PictureDetail(
        override val detail: Picture,
        override val commentList: CommentList
) : ObjectDetail<Picture>