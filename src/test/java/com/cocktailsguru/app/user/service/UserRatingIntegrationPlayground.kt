package com.cocktailsguru.app.user.service

import com.cocktailsguru.app.AuthenticatedIntegrationConfiguration
import com.cocktailsguru.app.IntegrationTestApp
import com.cocktailsguru.app.MockRequestUtils
import com.cocktailsguru.app.user.controller.UserRatingController
import com.cocktailsguru.app.user.domain.rating.RatingResultType
import com.cocktailsguru.app.user.domain.rating.RatingType
import com.cocktailsguru.app.user.dto.rating.RateObjectRequestDto
import com.cocktailsguru.app.user.dto.rating.RateObjectResultDto
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import org.junit.Before
import org.junit.Ignore
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType.APPLICATION_JSON_VALUE
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.context.WebApplicationContext
import kotlin.test.assertEquals
import kotlin.test.assertFalse

@RunWith(SpringRunner::class)
@SpringBootTest(classes = [(IntegrationTestApp::class)])
@Transactional
class UserRatingIntegrationPlayground : AuthenticatedIntegrationConfiguration() {
    private lateinit var mockMvc: MockMvc

    @Autowired
    private lateinit var wac: WebApplicationContext

    @Autowired
    private lateinit var userService: UserService
    @Autowired
    private lateinit var userRatingService: UserRatingService


    private val objectMapper = jacksonObjectMapper()
    private val objectWriter = objectMapper.writer().withDefaultPrettyPrinter()

    @Before
    fun setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build()
    }


    @Test
    fun whenRequestingCocktailRatedWithoutValidUserTokenShouldReturnUnauthorized() {
        val requestDto = RateObjectRequestDto(
                1,
                RatingType.ONE
        )

        mockMvc.perform(
                post("/" + UserRatingController.USER_RATING_BASE_PATH + "/" + UserRatingController.RATE_COCKTAIL)
                        .content(objectWriter.writeValueAsString(requestDto))
                        .contentType(APPLICATION_JSON_VALUE))
                .andExpect(status().isUnauthorized)
                .andReturn()
    }


    @Test
    @Ignore
    fun whenRequestingCocktailRatedForAlreadyRatedCocktailShouldReturnNotice() {
        val requestDto = RateObjectRequestDto(
                1,
                RatingType.ONE
        )

        val result = mockMvc.perform(
                MockRequestUtils.addAdminHeaders(post("/" + UserRatingController.USER_RATING_BASE_PATH + "/" + UserRatingController.RATE_COCKTAIL))
                        .content(objectWriter.writeValueAsString(requestDto))
                        .contentType(APPLICATION_JSON_VALUE))
                .andExpect(status().isOk)
                .andReturn()

        val responseJson = result.response.contentAsString
        val response = objectMapper.readValue(responseJson, RateObjectResultDto::class.java)

        assertEquals(RatingResultType.ALREADY_RATED, response.resultType)
    }


    @Test
    fun whenRequestingCocktailRatingForNotExistingCocktailShouldReturnNotice() {
        val requestDto = RateObjectRequestDto(
                -1,
                RatingType.ONE
        )

        val result = mockMvc.perform(
                MockRequestUtils.addAdminHeaders(post("/" + UserRatingController.USER_RATING_BASE_PATH + "/" + UserRatingController.RATE_COCKTAIL))
                        .content(objectWriter.writeValueAsString(requestDto))
                        .contentType(APPLICATION_JSON_VALUE))
                .andExpect(status().isOk)
                .andReturn()

        val responseJson = result.response.contentAsString
        val response = objectMapper.readValue(responseJson, RateObjectResultDto::class.java)

        assertEquals(RatingResultType.OBJECT_NOT_FOUND, response.resultType)
    }

    @Test
    @Ignore
    fun whenRequestingCocktailRatingShouldSetAsFavorite() {
        val cocktailId = 2L
        val requestDto = RateObjectRequestDto(
                cocktailId,
                RatingType.ONE
        )

        val result = mockMvc.perform(
                MockRequestUtils.addAdminHeaders(post("/" + UserRatingController.USER_RATING_BASE_PATH + "/" + UserRatingController.RATE_COCKTAIL))
                        .content(objectWriter.writeValueAsString(requestDto))
                        .contentType(APPLICATION_JSON_VALUE))
                .andExpect(status().isOk)
                .andReturn()

        val responseJson = result.response.contentAsString
        val response = objectMapper.readValue(responseJson, RateObjectResultDto::class.java)

        assertEquals(RatingResultType.OK, response.resultType)
    }

    @Test
    fun givenExistingUserRatingsWhenRequestingRatingsForUserShouldReturnNonEmptyList() {
        val user = userService.findUserById(6)

        val ratingsOfUser = userRatingService.getRatingsOfUser(user!!)

        assertFalse(ratingsOfUser.isEmpty())
    }
}