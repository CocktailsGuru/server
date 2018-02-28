package com.cocktailsguru.app.picture.repository

import com.cocktailsguru.app.cocktail.domain.CocktailObjectType
import com.cocktailsguru.app.picture.domain.Picture
import org.springframework.data.domain.Pageable
import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.stereotype.Repository

@Repository
interface PictureRepository : PagingAndSortingRepository<Picture, Long> {
    fun findByObjectTypeAndObjectForeignKey(objectType: CocktailObjectType, objectForeignKey: Long, pageable: Pageable): List<Picture>

//    fun findFirstByObjectTypeAndObjectForeignKey(objectType: CocktailObjectType, objectForeignKey: Long): Picture?
}
