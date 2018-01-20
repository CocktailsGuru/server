package com.cocktailsguru.app.cocktail.dto.list;

import com.cocktailsguru.app.common.dto.PagingDto;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class CocktailListResponseDto {
    private List<CocktailListItemDto> cocktailList;
    private PagingDto pagingInfo;
}
