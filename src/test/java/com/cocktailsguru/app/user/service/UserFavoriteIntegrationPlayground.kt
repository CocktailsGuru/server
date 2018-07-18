package com.cocktailsguru.app.user.service

import com.cocktailsguru.app.IntegrationTestApp
import com.cocktailsguru.app.MockRequestUtils
import com.cocktailsguru.app.user.controller.UserFavoriteController
import com.cocktailsguru.app.user.domain.favorite.SetFavoriteResultType
import com.cocktailsguru.app.user.dto.favorite.SetFavoriteRequestDto
import com.cocktailsguru.app.user.dto.favorite.SetFavoriteResponseDto
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
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
import kotlin.test.assertEquals

@RunWith(SpringRunner::class)
@SpringBootTest(classes = [(IntegrationTestApp::class)])
@Transactional
class UserFavoriteIntegrationPlayground {
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
    @Ignore
    fun whenRequestingCocktailAsFavoriteForAlreadyFavoriteCocktailShouldReturnNotice() {
        val requestDto = SetFavoriteRequestDto(
                1
        )

        val result = mockMvc.perform(
                MockRequestUtils.addAdminHeaders(post("/" + UserFavoriteController.USER_FAVORITE_BASE_PATH + "/" + UserFavoriteController.FAVORITE_COCKTAIL))
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
                -1
        )

        val result = mockMvc.perform(
                MockRequestUtils.addAdminHeaders(post("/" + UserFavoriteController.USER_FAVORITE_BASE_PATH + "/" + UserFavoriteController.FAVORITE_COCKTAIL))
                        .content(objectWriter.writeValueAsString(requestDto))
                        .contentType(APPLICATION_JSON_VALUE))
                .andExpect(status().isOk)
                .andReturn()

        val responseJson = result.response.contentAsString
        val response = objectMapper.readValue(responseJson, SetFavoriteResponseDto::class.java)

        assertEquals(SetFavoriteResultType.OBJECT_NOT_FOUND, response.resultType)
    }

    @Test
    @Ignore
    fun whenRequestingCocktailAsFavoriteShouldSetAsFavorite() {
        val cocktailId = 100L
        val requestDto = SetFavoriteRequestDto(
                cocktailId
        )

        val result = mockMvc.perform(
                MockRequestUtils.addAdminHeaders(post("/" + UserFavoriteController.USER_FAVORITE_BASE_PATH + "/" + UserFavoriteController.FAVORITE_COCKTAIL))
                        .content(objectWriter.writeValueAsString(requestDto))
                        .contentType(APPLICATION_JSON_VALUE))
                .andExpect(status().isOk)
                .andReturn()

        val responseJson = result.response.contentAsString
        val response = objectMapper.readValue(responseJson, SetFavoriteResponseDto::class.java)

        assertEquals(SetFavoriteResultType.OK, response.resultType)
    }


    @Test
    fun whenRequestingPictureAsFavoriteWithoutValidUserTokenShouldReturnUnauthorized() {
        val requestDto = SetFavoriteRequestDto(
                1
        )

        mockMvc.perform(
                post("/" + UserFavoriteController.USER_FAVORITE_BASE_PATH + "/" + UserFavoriteController.FAVORITE_PICTURE)
                        .content(objectWriter.writeValueAsString(requestDto))
                        .contentType(APPLICATION_JSON_VALUE))
                .andExpect(status().isUnauthorized)
                .andReturn()
    }


    @Test
    @Ignore
    fun whenRequestingPictureAsFavoriteForAlreadyFavoriteCocktailShouldReturnNotice() {
        val requestDto = SetFavoriteRequestDto(
                1
        )

        val result = mockMvc.perform(
                MockRequestUtils.addAdminHeaders(post("/" + UserFavoriteController.USER_FAVORITE_BASE_PATH + "/" + UserFavoriteController.FAVORITE_PICTURE))
                        .content(objectWriter.writeValueAsString(requestDto))
                        .contentType(APPLICATION_JSON_VALUE))
                .andExpect(status().isOk)
                .andReturn()

        val responseJson = result.response.contentAsString
        val response = objectMapper.readValue(responseJson, SetFavoriteResponseDto::class.java)

        assertEquals(SetFavoriteResultType.ALREADY_FAVORITE, response.resultType)
    }


    @Test
    fun whenRequestingPictureAsFavoriteForNotExistingCocktailShouldReturnNotice() {
        val requestDto = SetFavoriteRequestDto(
                -1
        )

        val result = mockMvc.perform(
                MockRequestUtils.addAdminHeaders(post("/" + UserFavoriteController.USER_FAVORITE_BASE_PATH + "/" + UserFavoriteController.FAVORITE_PICTURE))
                        .content(objectWriter.writeValueAsString(requestDto))
                        .contentType(APPLICATION_JSON_VALUE))
                .andExpect(status().isOk)
                .andReturn()

        val responseJson = result.response.contentAsString
        val response = objectMapper.readValue(responseJson, SetFavoriteResponseDto::class.java)

        assertEquals(SetFavoriteResultType.OBJECT_NOT_FOUND, response.resultType)
    }

    @Test
    @Ignore
    fun whenRequestingPictureAsFavoriteShouldSetAsFavorite() {
        val cocktailId = 2L
        val requestDto = SetFavoriteRequestDto(
                cocktailId
        )

        val result = mockMvc.perform(
                MockRequestUtils.addAdminHeaders(post("/" + UserFavoriteController.USER_FAVORITE_BASE_PATH + "/" + UserFavoriteController.FAVORITE_PICTURE))
                        .content(objectWriter.writeValueAsString(requestDto))
                        .contentType(APPLICATION_JSON_VALUE))
                .andExpect(status().isOk)
                .andReturn()

        val responseJson = result.response.contentAsString
        val response = objectMapper.readValue(responseJson, SetFavoriteResponseDto::class.java)

        assertEquals(SetFavoriteResultType.OK, response.resultType)
    }
}