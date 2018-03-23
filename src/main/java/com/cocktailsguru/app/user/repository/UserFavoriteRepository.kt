package com.cocktailsguru.app.user.repository

import com.cocktailsguru.app.cocktail.domain.CocktailObjectType
import com.cocktailsguru.app.user.domain.User
import com.cocktailsguru.app.user.domain.favorite.UserFavorite
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface UserFavoriteRepository : CrudRepository<UserFavorite, Long> {
    fun findByObjectTypeAndObjectForeignKey(objectType: CocktailObjectType, objectForeignKey: Long): List<UserFavorite>

    fun countByObjectTypeAndObjectForeignKey(objectType: CocktailObjectType, objectForeignKey: Long): Long

    fun existsByUserAndObjectTypeAndObjectForeignKey(user: User, objectType: CocktailObjectType, objectForeignKey: Long): Boolean
}