package com.cocktailsguru.app.user.controller

import com.cocktailsguru.app.common.dto.UnauthorizedException
import com.cocktailsguru.app.user.controller.UserFavoriteController.Companion.USER_FAVORITE_BASE_PATH
import com.cocktailsguru.app.user.domain.favorite.SetFavoriteResultType
import com.cocktailsguru.app.user.dto.favorite.SetFavoriteRequestDto
import com.cocktailsguru.app.user.dto.favorite.SetFavoriteResponseDto
import com.cocktailsguru.app.user.service.UserFavoriteService
import com.cocktailsguru.app.utils.loggerFor
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.access.annotation.Secured
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController

@RestController
@Secured(value = ["ROLE_MOBILE"])
@RequestMapping(USER_FAVORITE_BASE_PATH)
open class UserFavoriteController @Autowired constructor(
        private val userFavoriteService: UserFavoriteService
) {

    private val logger = loggerFor(javaClass)

    companion object {
        const val USER_FAVORITE_BASE_PATH = "favorite"
        const val FAVORITE_COCKTAIL = "cocktail"
        const val FAVORITE_PICTURE = "picture"
    }


    @RequestMapping(value = [FAVORITE_COCKTAIL], produces = ["application/json"], method = [RequestMethod.POST])
    open fun setCocktailFavorite(@RequestBody requestDto: SetFavoriteRequestDto): SetFavoriteResponseDto {
        logger.info("Requested cocktail set as favorite - {}", requestDto)

        val setFavoriteResultType = userFavoriteService.setCocktailAsFavorite(requestDto.objectId, requestDto.userTokenDto.toUserTokenToVerify())

        return when (setFavoriteResultType) {
            SetFavoriteResultType.USER_NOT_FOUND -> throw UnauthorizedException()
            else -> SetFavoriteResponseDto(setFavoriteResultType)
        }
    }

    @RequestMapping(value = [FAVORITE_PICTURE], produces = ["application/json"], method = [RequestMethod.POST])
    open fun setPictureFavorite(@RequestBody requestDto: SetFavoriteRequestDto): SetFavoriteResponseDto {
        logger.info("Requested picture set as favorite - {}", requestDto)

        val setFavoriteResultType = userFavoriteService.setPictureAsFavorite(requestDto.objectId, requestDto.userTokenDto.toUserTokenToVerify())

        return when (setFavoriteResultType) {
            SetFavoriteResultType.USER_NOT_FOUND -> throw UnauthorizedException()
            else -> SetFavoriteResponseDto(setFavoriteResultType)
        }
    }
}