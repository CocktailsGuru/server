package com.cocktailsguru.app.user.service

import com.cocktailsguru.app.cocktail.domain.CocktailObjectType
import com.cocktailsguru.app.cocktail.service.CocktailService
import com.cocktailsguru.app.picture.service.PictureService
import com.cocktailsguru.app.user.domain.User
import com.cocktailsguru.app.user.domain.favorite.SetFavoriteResultType
import com.cocktailsguru.app.user.domain.favorite.UserFavorite
import com.cocktailsguru.app.user.repository.UserFavoriteRepository
import org.springframework.stereotype.Service
import javax.transaction.Transactional

@Service
open class UserFavoriteServiceImpl(
        private val userFavoriteRepository: UserFavoriteRepository,
        private val cocktailService: CocktailService,
        private val pictureService: PictureService
) : UserFavoriteService {

    @Transactional
    override fun setPictureAsFavorite(pictureId: Long, user: User): SetFavoriteResultType {
        val picture = pictureService.findPicture(pictureId) ?: return SetFavoriteResultType.OBJECT_NOT_FOUND

        val alreadyFavorite = userFavoriteRepository.existsByUserAndObjectTypeAndObjectForeignKey(user, CocktailObjectType.USER_PICTURE, picture.id)

        if (alreadyFavorite) {
            return SetFavoriteResultType.ALREADY_FAVORITE
        }

        val userFavorite = UserFavorite(
                0,
                CocktailObjectType.USER_PICTURE,
                picture.id,
                user
        )

        userFavoriteRepository.save(userFavorite)
        user.numPicturesFav++
        picture.numFavorite++

        return SetFavoriteResultType.OK
    }


    @Transactional
    override fun setCocktailAsFavorite(cocktailId: Long, user: User): SetFavoriteResultType {
        val cocktail = cocktailService.findCocktail(cocktailId) ?: return SetFavoriteResultType.OBJECT_NOT_FOUND

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