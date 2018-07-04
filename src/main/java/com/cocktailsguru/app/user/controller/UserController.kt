package com.cocktailsguru.app.user.controller

import com.cocktailsguru.app.user.controller.UserController.Companion.USER_BASE_PATH
import com.cocktailsguru.app.user.domain.Gender
import com.cocktailsguru.app.user.domain.UserRegistrationRequest
import com.cocktailsguru.app.user.domain.UserRegistrationType
import com.cocktailsguru.app.user.dto.registration.RegisterUserRequestDto
import com.cocktailsguru.app.user.dto.registration.RegisterUserResponseDto
import com.cocktailsguru.app.user.service.UserService
import com.cocktailsguru.app.utils.loggerFor
import org.springframework.security.access.annotation.Secured
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController

@RestController
@Secured(value = ["ROLE_MOBILE"])
@RequestMapping(USER_BASE_PATH)
open class UserController(
        private val userService: UserService
) {

    private val logger = loggerFor(javaClass)

    companion object {
        const val USER_BASE_PATH = "user"
        const val REGISTER_USER_PATH = "register"
    }


    @RequestMapping(value = [REGISTER_USER_PATH], produces = ["application/json"], method = [RequestMethod.POST])
    open fun registerUser(@RequestBody requestDto: RegisterUserRequestDto): RegisterUserResponseDto {
        logger.info("Requested user registration - {}", requestDto)
        val registrationRequest = UserRegistrationRequest(
                requestDto.externalUserId,
                requestDto.name,
                Gender.valueOf(requestDto.gender),
                UserRegistrationType.valueOf(requestDto.registrationType),
                requestDto.image,
                requestDto.image,
                requestDto.language
        )

        if (registrationRequest.registrationType == UserRegistrationType.FB) {
            checkNotNull(registrationRequest.language)
        }

        val registrationResult = userService.registerUser(registrationRequest)
        return RegisterUserResponseDto(registrationResult)
    }

}