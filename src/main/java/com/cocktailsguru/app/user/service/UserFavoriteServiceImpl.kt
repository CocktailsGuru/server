package com.cocktailsguru.app.user.service

import com.cocktailsguru.app.cocktail.domain.CocktailObjectType
import com.cocktailsguru.app.user.domain.UserFavorite
import com.cocktailsguru.app.user.repository.UserFavoriteRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class UserFavoriteServiceImpl @Autowired constructor(private val userFavoriteRepository: UserFavoriteRepository) : UserFavoriteService {

    override fun getCountOfFavoriteObjects(objectType: CocktailObjectType, id: Long): Long {
        return userFavoriteRepository.countByObjectTypeAndObjectForeignKey(objectType, id)
    }

    override fun getFavoriteObjects(objectType: CocktailObjectType, id: Long): List<UserFavorite> {
        return userFavoriteRepository.findByObjectTypeAndObjectForeignKey(objectType, id)
    }

}