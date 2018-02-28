package com.cocktailsguru.app.picture.service

import com.cocktailsguru.app.cocktail.domain.Cocktail
import com.cocktailsguru.app.cocktail.domain.CocktailObjectType
import com.cocktailsguru.app.common.domain.PagingInfo
import com.cocktailsguru.app.picture.domain.PictureList

interface PictureService {
    fun getPictureList(cocktail: Cocktail, pagingInfo: PagingInfo): PictureList

    fun getPictureListForObject(objectType: CocktailObjectType, id: Long, pagingInfo: PagingInfo): PictureList

//    fun findFirstPictureForObject(objectType: CocktailObjectType, id: Long): Picture?
}