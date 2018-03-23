package com.cocktailsguru.app.user.service

import com.cocktailsguru.app.cocktail.domain.CocktailObjectType
import com.cocktailsguru.app.cocktail.service.CocktailService
import com.cocktailsguru.app.user.domain.UserTokenToVerify
import com.cocktailsguru.app.user.domain.favorite.SetFavoriteResultType
import com.cocktailsguru.app.user.domain.favorite.UserFavorite
import com.cocktailsguru.app.user.repository.UserFavoriteRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import javax.transaction.Transactional

@Service
open class UserFavoriteServiceImpl @Autowired constructor(
        private val userFavoriteRepository: UserFavoriteRepository,
        private val userService: UserService,
        private val cocktailService: CocktailService
) : UserFavoriteService {


    @Transactional
    override fun setCocktailAsFavorite(cocktailId: Long, userToken: UserTokenToVerify): SetFavoriteResultType {
        val cocktail = cocktailService.findCocktail(cocktailId) ?: return SetFavoriteResultType.OBJECT_NOT_FOUND

        val user = userService.verifyUser(userToken) ?: return SetFavoriteResultType.USER_NOT_FOUND

        val alreadyFavorite = userFavoriteRepository.existsByUserAndObjectTypeAndObjectForeignKey(user, CocktailObjectType.COCKTAIL, cocktail.id)

        if (alreadyFavorite) {
            return SetFavoriteResultType.ALREADY_FAVORITE
        }

        val userFavorite = UserFavorite(
                0,
                CocktailObjectType.COCKTAIL,
                cocktail.id,
                user
        )

        userFavoriteRepository.save(userFavorite)
        user.numCocktailsFav++
        cocktail.numOfFavorite++

        return SetFavoriteResultType.OK
    }

    override fun getCountOfFavoriteObjects(objectType: CocktailObjectType, id: Long): Long {
        return userFavoriteRepository.countByObjectTypeAndObjectForeignKey(objectType, id)
    }

    override fun getFavoriteObjects(objectType: CocktailObjectType, id: Long): List<UserFavorite> {
        return userFavoriteRepository.findByObjectTypeAndObjectForeignKey(objectType, id)
    }

}