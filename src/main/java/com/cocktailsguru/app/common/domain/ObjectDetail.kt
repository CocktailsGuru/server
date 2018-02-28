package com.cocktailsguru.app.common.domain

import com.cocktailsguru.app.comment.domain.CommentList

interface ObjectDetail<out T> {
    val detail: T
    val commentList: CommentList
}