package com.cocktailsguru.app.cocktail.dto.list

import com.cocktailsguru.app.cocktail.domain.CocktailList
import com.cocktailsguru.app.common.dto.ListResponseDto
import com.cocktailsguru.app.common.dto.PagingDto

data class CocktailListResponseDto(
        override val list: List<CocktailListItemDto>,
        override val pagingInfo: PagingDto
) : ListResponseDto<CocktailListItemDto> {
    constructor(cocktailList: CocktailList) : this(
            cocktailList.objectList.map { CocktailListItemDto(it) },
            PagingDto(cocktailList.pagingInfo)
    )
}