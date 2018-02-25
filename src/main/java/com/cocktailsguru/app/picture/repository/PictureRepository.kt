package com.cocktailsguru.app.picture.repository

import com.cocktailsguru.app.cocktail.domain.CocktailObjectType
import com.cocktailsguru.app.picture.domain.Picture
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface PictureRepository : CrudRepository<Picture, Long> {
    fun findByObjectTypeAndObjectForeignKey(objectType: CocktailObjectType, objectForeignKey: Long): List<Picture>
}
