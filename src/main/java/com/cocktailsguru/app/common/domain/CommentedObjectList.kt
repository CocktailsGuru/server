package com.cocktailsguru.app.common.domain

import com.cocktailsguru.app.comment.domain.CommentList

interface CommentedObjectList<out T> : ObjectList<T> {
    val commentList: CommentList
}