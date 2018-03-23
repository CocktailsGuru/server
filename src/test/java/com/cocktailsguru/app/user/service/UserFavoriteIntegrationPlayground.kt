package com.cocktailsguru.app.user.service

import com.cocktailsguru.app.IntegrationTestApp
import com.cocktailsguru.app.user.controller.UserFavoriteController
import com.cocktailsguru.app.user.domain.favorite.SetFavoriteResultType
import com.cocktailsguru.app.user.dto.UserTokenDto
import com.cocktailsguru.app.user.dto.favorite.SetFavoriteRequestDto
import com.cocktailsguru.app.user.dto.favorite.SetFavoriteResponseDto
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import org.junit.Before
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
import kotlin.test.assertEquals

@RunWith(SpringRunner::class)
@SpringBootTest(classes = [(IntegrationTestApp::class)])
@Transactional
open class UserFavoriteIntegrationPlayground {
    private lateinit var mockMvc: MockMvc

    @Autowired
    private lateinit var wac: WebApplicationContext


    private val objectMapper = jacksonObjectMapper()
    private val objectWriter = objectMapper.writer().withDefaultPrettyPrinter()

    @Before
    fun setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build()
    }


    @Test
    fun whenRequestingCocktailAsFavoriteWithoutValidUserTokenShouldReturnUnauthorized() {
        val requestDto = SetFavoriteRequestDto(
                UserTokenDto(1, "1"),
                1
        )

        mockMvc.perform(
                post("/" + UserFavoriteController.USER_FAVORITE_BASE_PATH + "/" + UserFavoriteController.FAVORITE_COCKTAIL)
                        .content(objectWriter.writeValueAsString(requestDto))
                        .contentType(APPLICATION_JSON_VALUE))
                .andExpect(status().isUnauthorized)
                .andReturn()
    }


    @Test
    fun whenRequestingCocktailAsFavoriteForAlreadyFavoriteCocktailShouldReturnNotice() {
        val requestDto = SetFavoriteRequestDto(
                UserTokenDto(6, "adminToken"),
                1
        )

        val result = mockMvc.perform(
                post("/" + UserFavoriteController.USER_FAVORITE_BASE_PATH + "/" + UserFavoriteController.FAVORITE_COCKTAIL)
                        .content(objectWriter.writeValueAsString(requestDto))
                        .contentType(APPLICATION_JSON_VALUE))
                .andExpect(status().isOk)
                .andReturn()

        val responseJson = result.response.contentAsString
        val response = objectMapper.readValue(responseJson, SetFavoriteResponseDto::class.java)

        assertEquals(SetFavoriteResultType.ALREADY_FAVORITE, response.resultType)
    }


    @Test
    fun whenRequestingCocktailAsFavoriteForNotExistingCocktailShouldReturnNotice() {
        val requestDto = SetFavoriteRequestDto(
                UserTokenDto(6, "adminToken"),
                -1
        )

        val result = mockMvc.perform(
                post("/" + UserFavoriteController.USER_FAVORITE_BASE_PATH + "/" + UserFavoriteController.FAVORITE_COCKTAIL)
                        .content(objectWriter.writeValueAsString(requestDto))
                        .contentType(APPLICATION_JSON_VALUE))
                .andExpect(status().isOk)
                .andReturn()

        val responseJson = result.response.contentAsString
        val response = objectMapper.readValue(responseJson, SetFavoriteResponseDto::class.java)

        assertEquals(SetFavoriteResultType.OBJECT_NOT_FOUND, response.resultType)
    }

    @Test
    fun whenRequestingCocktailAsFavoriteShouldSetAsFavorite() {
        val cocktailId = 2L
        val requestDto = SetFavoriteRequestDto(
                UserTokenDto(6, "adminToken"),
                cocktailId
        )

        val result = mockMvc.perform(
                post("/" + UserFavoriteController.USER_FAVORITE_BASE_PATH + "/" + UserFavoriteController.FAVORITE_COCKTAIL)
                        .content(objectWriter.writeValueAsString(requestDto))
                        .contentType(APPLICATION_JSON_VALUE))
                .andExpect(status().isOk)
                .andReturn()

        val responseJson = result.response.contentAsString
        val response = objectMapper.readValue(responseJson, SetFavoriteResponseDto::class.java)

        assertEquals(SetFavoriteResultType.OK, response.resultType)
    }
}