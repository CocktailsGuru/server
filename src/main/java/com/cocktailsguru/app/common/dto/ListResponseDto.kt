package com.cocktailsguru.app.common.dto

interface ListResponseDto<out T> {
    val list: List<T>
    val pagingInfo: PagingDto
}