package com.cocktailsguru.app.common.service

import com.cocktailsguru.app.cocktail.domain.CocktailObjectType
import com.cocktailsguru.app.cocktail.service.CocktailService
import com.cocktailsguru.app.ingredient.service.IngredientService
import com.cocktailsguru.app.picture.service.PictureService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

//@Service
class CocktailObjectServiceImpl @Autowired constructor(
        private val cocktailService: CocktailService,
        private val ingredientService: IngredientService,
        private val pictureService: PictureService
) : CocktailObjectService {

//    override fun findObjectName(id: Long, objectType: CocktailObjectType): String? {
//        return when (objectType) {
//            CocktailObjectType.COCKTAIL -> cocktailService.getCocktailDetail(id)?.name
//            CocktailObjectType.INGREDIENT -> ingredientService.findIngredientDetail(id)?.name
//            CocktailObjectType.USER_PICTURE -> pictureService.findFirstPictureForObject(objectType, id)?.objectName
//            else -> null
//        }
//    }

}