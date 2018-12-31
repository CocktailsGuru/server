package com.cocktailsguru.app.user.service

import com.cocktailsguru.app.cocktail.domain.CocktailObjectType
import com.cocktailsguru.app.user.controller.UserController
import com.cocktailsguru.app.user.domain.*
import com.cocktailsguru.app.user.domain.registration.UserRegistrationResultType
import com.cocktailsguru.app.user.dto.registration.RegisterUserRequestDto
import com.cocktailsguru.app.user.dto.registration.RegisterUserResponseDto
import com.cocktailsguru.app.user.repository.UserTokenRepository
import com.cocktailsguru.app.utils.loggerFor
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import junit.framework.TestCase.*
import org.junit.Before
import org.junit.Ignore
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType.APPLICATION_JSON_VALUE
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.context.WebApplicationContext

@RunWith(SpringRunner::class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Transactional
class UserIntegrationPlaygroundTest {

    private val log = loggerFor(javaClass)

    private lateinit var mockMvc: MockMvc

    @Autowired
    private lateinit var userService: UserService

    @Autowired
    private lateinit var wac: WebApplicationContext

    @Autowired
    lateinit var userFavoriteService: UserFavoriteService

    @Autowired
    private lateinit var userController: UserController

    @Autowired
    private lateinit var userTokenRepository: UserTokenRepository

    private val objectMapper = jacksonObjectMapper()
    private val objectWriter = objectMapper.writer().withDefaultPrettyPrinter()

    @Before
    fun setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build()
    }


    @Test
    @Ignore
    fun shouldFindUserById() {
        assertNull(userService.findUserById(1))

        val googleUser = userService.findUserById(7651)
        assertNotNull(googleUser)
        assertTrue(googleUser is GoogleUser)

        val fbUser = userService.findUserById(7169)
        assertNotNull(fbUser)
        assertTrue(fbUser is FbUser)
    }

    @Test
    @Ignore
    fun shouldReturnNotNullListOfFavorite() {
        val favoriteObjects = userFavoriteService.getFavoriteObjects(CocktailObjectType.COCKTAIL, 54L)
        assertFalse(favoriteObjects.isEmpty())
    }

    @Test
    fun shouldRegisterExistingFbUser() {
        val userRegistrationRequest = UserRegistrationRequest(
                "123456789",
                "anyName",
                Gender.MALE,
                UserRegistrationType.FB,
                "anyImaage",
                "nwm",
                "anyLanguage"
        )

        val registrationResult = userService.registerUser(userRegistrationRequest)

        assertNotNull(registrationResult)
        assertTrue(registrationResult.user is FbUser)
        assertFalse(0L == registrationResult.user.id)

        val foundUser = userService.findUserById(registrationResult.user.id)


        assertNotNull(foundUser)
        assertEquals(registrationResult.user, foundUser)
        assertEquals(registrationResult.registrationResultType, UserRegistrationResultType.NEW_REGISTRATION)

        val userToken = userTokenRepository.findFirstByUserIdAndValidTrue(foundUser!!.id)
        assertNotNull(userToken)
        assertEquals(userToken, registrationResult.token)
        assertTrue(userToken!!.valid)


        val secondRegistrationResult = userService.registerUser(userRegistrationRequest)

        assertNotNull(secondRegistrationResult)
        assertTrue(secondRegistrationResult.user is FbUser)
        assertFalse(0L == secondRegistrationResult.user.id)

        val newFoundUser = userService.findUserById(registrationResult.user.id)

        assertNotNull(newFoundUser)
        assertEquals(secondRegistrationResult.user, newFoundUser)
        assertEquals(secondRegistrationResult.registrationResultType, UserRegistrationResultType.EXISTING_USER)

        val newUserToken = userTokenRepository.findFirstByUserIdAndValidTrue(foundUser.id)
        assertNotNull(newUserToken)
        assertEquals(newUserToken, registrationResult.token)
        assertTrue(newUserToken!!.valid)
    }

    @Test
    fun shouldRegisterNewGoogleUser() {
        val userRegistrationRequest = UserRegistrationRequest(
                "123456789",
                "anyName",
                Gender.MALE,
                UserRegistrationType.GOOGLE,
                "anyImaage",
                "nwm",
                null
        )

        val registrationResult = userService.registerUser(userRegistrationRequest)

        assertNotNull(registrationResult)
        assertTrue(registrationResult.user is GoogleUser)
        assertFalse(0L == registrationResult.user.id)

        val foundUser = userService.findUserById(registrationResult.user.id)

        assertNotNull(foundUser)
        assertEquals(registrationResult.user, foundUser)
        assertEquals(registrationResult.registrationResultType, UserRegistrationResultType.NEW_REGISTRATION)

        val userToken = userTokenRepository.findFirstByUserIdAndValidTrue(foundUser!!.id)
        assertNotNull(userToken)
        assertEquals(userToken, registrationResult.token)
        assertTrue(userToken!!.valid)
    }


    @Test
    fun shouldRegisterNewFbUserViaController() {
        val requestDto = RegisterUserRequestDto(
                "345678adas",
                "any name",
                Gender.MALE.name,
                UserRegistrationType.FB.name,
                "any image",
                "nwm",
                "lang"
        )

        val requestJson = objectWriter.writeValueAsString(requestDto)

        val result = mockMvc.perform(
                post("/" + UserController.USER_BASE_PATH + "/" + UserController.REGISTER_USER_PATH)
                        .content(requestJson)
                        .contentType(APPLICATION_JSON_VALUE))
                .andExpect(status().isOk)
                .andReturn()
        val responseJson = result.response.contentAsString
        val response = objectMapper.readValue(responseJson, RegisterUserResponseDto::class.java)

        assertNotNull(response)
        assertFalse(0L == response.id)

        val userToken = userTokenRepository.findFirstByUserIdAndValidTrue(response.id)

        assertNotNull(userToken)
        assertEquals(response.token, userToken!!.token)
    }


    @Test
    fun shouldRegisterNewGoogleUserViaController() {
        val requestDto = RegisterUserRequestDto(
                "345678adas",
                "any name",
                Gender.FEMALE.name,
                UserRegistrationType.GOOGLE.name,
                "any image",
                "nwm",
                null
        )

        val requestJson = objectWriter.writeValueAsString(requestDto)

        val result = mockMvc.perform(
                post("/" + UserController.USER_BASE_PATH + "/" + UserController.REGISTER_USER_PATH)
                        .content(requestJson)
                        .contentType(APPLICATION_JSON_VALUE))
                .andExpect(status().isOk)
                .andReturn()
        val responseJson = result.response.contentAsString
        val response = objectMapper.readValue(responseJson, RegisterUserResponseDto::class.java)

        assertNotNull(response)
        assertFalse(0L == response.id)
    }

    @Test(expected = IllegalStateException::class)
    fun givenNotProvidedLanguageWhenRequestingUserRegistrationShouldFail() {
        val requestDto = RegisterUserRequestDto(
                "345678adas",
                "any name",
                Gender.MALE.name,
                UserRegistrationType.FB.name,
                "any image",
                "nwm",
                null
        )

        userController.registerUser(requestDto)
    }


    @Test(expected = IllegalArgumentException::class)
    fun givenProvidedWrongGenderWhenRequestingUserRegistrationShouldFail() {
        val requestDto = RegisterUserRequestDto(
                "345678adas",
                "any name",
                "unknown gender",
                UserRegistrationType.FB.name,
                "any image",
                "nwm",
                null
        )

        userController.registerUser(requestDto)
    }

    @Test(expected = IllegalArgumentException::class)
    fun givenProvidedWrongRegistrationTypeWhenRequestingUserRegistrationShouldFail() {
        val requestDto = RegisterUserRequestDto(
                "345678adas",
                "any name",
                Gender.MALE.name,
                "djsapodjpoas",
                "any image",
                "nwm",
                null
        )

        userController.registerUser(requestDto)
    }
}
