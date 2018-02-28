package com.cocktailsguru.app.cocktail.dto.detail

import com.cocktailsguru.app.cocktail.domain.CocktailGlass

class CocktailGlassDto(
        val id: Long,
        val name: String
) {
    constructor(glass: CocktailGlass) : this(
            glass.id,
            glass.name
    )
}

