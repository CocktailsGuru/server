package com.cocktailsguru.app.user.controller

import com.cocktailsguru.app.user.domain.Gender
import com.cocktailsguru.app.user.domain.UserRegistrationRequest
import com.cocktailsguru.app.user.domain.UserRegistrationType
import com.cocktailsguru.app.user.dto.registration.RegisterUserRequestDto
import com.cocktailsguru.app.user.dto.registration.RegisterUserResponseDto
import com.cocktailsguru.app.user.service.UserService
import com.cocktailsguru.app.utils.loggerFor
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController

@RestController
class UserController @Autowired constructor(
        private val userService: UserService
) {

    val logger = loggerFor(javaClass)

    companion object {
        const val REGISTER_USER_PATH = "/registerUser"
    }


    @RequestMapping(value = [REGISTER_USER_PATH], produces = ["application/json"], method = [RequestMethod.POST])
    fun registerUser(@RequestBody requestDto: RegisterUserRequestDto): RegisterUserResponseDto {
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

        val user = userService.registerUser(registrationRequest)
        return RegisterUserResponseDto(user.id)
    }

}