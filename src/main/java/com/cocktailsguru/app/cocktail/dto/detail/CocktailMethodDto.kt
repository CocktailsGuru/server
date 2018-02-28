package com.cocktailsguru.app.cocktail.dto.detail

import com.cocktailsguru.app.cocktail.domain.CocktailMethod

class CocktailMethodDto(
        val id: Int,
        val name: String,
        val description: String
) {
    constructor(method: CocktailMethod) : this(
            method.id,
            method.name,
            method.description
    )
}