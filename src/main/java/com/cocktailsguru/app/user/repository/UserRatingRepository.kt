package com.cocktailsguru.app.user.repository

import com.cocktailsguru.app.cocktail.domain.CocktailObjectType
import com.cocktailsguru.app.user.domain.User
import com.cocktailsguru.app.user.domain.rating.UserRating
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRatingRepository : CrudRepository<UserRating, Long> {
    fun existsByUserAndObjectTypeAndObjectForeignKey(user: User, objectType: CocktailObjectType, objectForeignKey: Long): Boolean

    fun findByObjectTypeAndObjectForeignKey(objectType: CocktailObjectType, objectForeignKey: Long): List<UserRating>

    fun findByUser(user: User): List<UserRating>
}