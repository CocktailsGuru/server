package com.cocktailsguru.app.user.controller

import com.cocktailsguru.app.common.dto.UnauthorizedException
import com.cocktailsguru.app.user.controller.UserRatingController.Companion.USER_RATING_BASE_PATH
import com.cocktailsguru.app.user.domain.favorite.SetFavoriteResultType
import com.cocktailsguru.app.user.dto.rating.RateObjectRequestDto
import com.cocktailsguru.app.user.dto.rating.RateObjectResultDto
import com.cocktailsguru.app.user.service.UserRatingService
import com.cocktailsguru.app.utils.loggerFor
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.access.annotation.Secured
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController

@RestController
@Secured(value = ["ROLE_MOBILE"])
@RequestMapping(USER_RATING_BASE_PATH)
open class UserRatingController @Autowired constructor(
        private val userRatingService: UserRatingService
) {

    private val logger = loggerFor(javaClass)

    companion object {
        const val USER_RATING_BASE_PATH = "rating"
        const val RATE_COCKTAIL = "cocktail"
    }


    @RequestMapping(value = [RATE_COCKTAIL], produces = ["application/json"], method = [RequestMethod.POST])
    open fun rateCocktail(@RequestBody requestDto: RateObjectRequestDto): RateObjectResultDto {
        logger.info("Requested cocktail rating - {}", requestDto)

        val ratingResultType = userRatingService.rateCocktail(requestDto.toRateObjectRequest())

        return when (ratingResultType) {
            SetFavoriteResultType.USER_NOT_FOUND -> throw UnauthorizedException()
            else -> RateObjectResultDto(ratingResultType)
        }
    }
}