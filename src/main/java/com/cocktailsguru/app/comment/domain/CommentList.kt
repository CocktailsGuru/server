package com.cocktailsguru.app.comment.domain

import com.cocktailsguru.app.common.domain.ObjectList
import com.cocktailsguru.app.common.domain.PagingInfo

data class CommentList(
        override val objectList: List<Comment>,
        override val pagingInfo: PagingInfo
) : ObjectList<Comment>