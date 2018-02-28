package com.cocktailsguru.app.cocktail.dto.detail

import com.cocktailsguru.app.cocktail.domain.Cocktail

data class CocktailDetailDto(
        val id: Long,
        val name: String,
        val totalVolume: Int,
        val alcoVolume: Int,
        val nonAlcoVolume: Int,
        val instructions: String,
        val garnish: String,
        val description: String,
        val imageName: String,
        val glass: CocktailGlassDto,
        val method: CocktailMethodDto,
        val ingredientList: List<Long>,
        val similarCocktailList: List<Long>,
        val numOfFavorite: Int,
        val calculatedRating: Double,
        val numRating1: Int,
        val numRating2: Int,
        val numRating3: Int,
        val numRating4: Int,
        val numRating5: Int,
        val alcoholVolume: Double,
        val numPictures: Int,
        val numComments: Int,
        val numShowed: Int
) {
    constructor(cocktail: Cocktail) : this(
            cocktail.id,
            cocktail.name,
            cocktail.totalVolume,
            cocktail.alcoVolume,
            cocktail.nonAlcoVolume,
            cocktail.instructions,
            cocktail.garnish,
            cocktail.description,
            cocktail.imageName,
            CocktailGlassDto(cocktail.glass),
            CocktailMethodDto(cocktail.method),
            cocktail.ingredientList.map { it.ingredient.id },
            cocktail.similarCocktailList.map { it.id },
            cocktail.numOfFavorite,
            cocktail.calculatedRating,
            cocktail.numRating1,
            cocktail.numRating2,
            cocktail.numRating3,
            cocktail.numRating4,
            cocktail.numRating5,
            cocktail.alcoholVolume,
            cocktail.numPictures,
            cocktail.numComments,
            cocktail.numShowed
    )
}
