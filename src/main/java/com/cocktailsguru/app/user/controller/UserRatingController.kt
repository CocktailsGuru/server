package com.cocktailsguru.app.user.controller

import com.cocktailsguru.app.user.controller.UserRatingController.Companion.USER_RATING_BASE_PATH
import com.cocktailsguru.app.user.dto.rating.RateObjectRequestDto
import com.cocktailsguru.app.user.dto.rating.RateObjectResultDto
import com.cocktailsguru.app.user.service.UserRatingService
import com.cocktailsguru.app.utils.loggerFor
import com.cocktailsguru.app.verification.service.UserVerificationService
import org.springframework.security.access.annotation.Secured
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController

@RestController
@Secured(value = ["ROLE_MOBILE"])
@RequestMapping(USER_RATING_BASE_PATH)
open class UserRatingController(
        private val userRatingService: UserRatingService,
        private val userVerificationService: UserVerificationService
) {

    private val logger = loggerFor(javaClass)

    companion object {
        const val USER_RATING_BASE_PATH = "rating"
        const val RATE_COCKTAIL = "cocktail"
    }


    @RequestMapping(value = [RATE_COCKTAIL], produces = ["application/json"], method = [RequestMethod.POST])
    open fun rateCocktail(@RequestBody requestDto: RateObjectRequestDto): RateObjectResultDto {
        logger.info("Requested cocktail rating - {}", requestDto)

        val loggedUser = userVerificationService.getLoggedUser()
        val ratingResultType = userRatingService.rateCocktail(requestDto.toRateObjectRequest(), loggedUser)

        return RateObjectResultDto(ratingResultType)
    }
}