package com.cocktailsguru.app.common.domain

interface ObjectList<out T> {
    val objectList: List<T>
    val pagingInfo: PagingInfo
}