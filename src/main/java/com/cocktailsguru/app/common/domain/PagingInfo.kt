package com.cocktailsguru.app.common.domain

import com.cocktailsguru.app.common.dto.PagingDto

data class PagingInfo(
        val pageNumber: Int,
        val pageSize: Int
) {
    constructor(pagingDto: PagingDto) : this(pagingDto.pageNumber, pagingDto.pageSize)
}