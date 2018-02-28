package com.cocktailsguru.app.cocktail.dto.list

import com.cocktailsguru.app.cocktail.domain.Cocktail

data class CocktailListItemDto(
        val id: Long,
        val name: String,
        val image: String,
        val calculatedRating: Double,
        val alcoVolume: Int,
        val numOfFavorite: Int
) {
    constructor(cocktail: Cocktail) : this(
            cocktail.id,
            cocktail.name,
            cocktail.imageName,
            cocktail.calculatedRating,
            cocktail.alcoVolume,
            cocktail.numOfFavorite
    )
}