package com.cocktailsguru.app.common.dto

import com.cocktailsguru.app.common.domain.PagingInfo

data class PagingDto(
        val pageNumber: Int,
        val pageSize: Int
) {
    constructor(pagingInfo: PagingInfo) : this(pagingInfo.pageNumber, pagingInfo.pageSize)
}
