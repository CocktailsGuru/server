package com.cocktailsguru.app.common.dto

data class ListResponseDto<out T>(
        val list: List<T>,
        val pagingInfo: PagingDto
)