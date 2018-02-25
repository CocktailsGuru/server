package com.cocktailsguru.app.picture.service

import com.cocktailsguru.app.cocktail.domain.CocktailObjectType
import com.cocktailsguru.app.picture.domain.Picture

interface PictureService {
    fun getPictureListForObject(objectType: CocktailObjectType, id: Long): List<Picture>
}