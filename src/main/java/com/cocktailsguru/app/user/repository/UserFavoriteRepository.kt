package com.cocktailsguru.app.user.repository

import com.cocktailsguru.app.cocktail.domain.CocktailObjectType
import com.cocktailsguru.app.user.domain.UserFavorite
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface UserFavoriteRepository : CrudRepository<UserFavorite, Long> {
    fun findByObjectTypeAndObjectForeignKey(objectType: CocktailObjectType, objectForeignKey: Long): List<UserFavorite>
}