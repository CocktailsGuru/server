package com.cocktailsguru.app.cocktail.service

import com.cocktailsguru.app.IntegrationTestApp
import com.cocktailsguru.app.cocktail.controller.CocktailController
import com.cocktailsguru.app.cocktail.dto.detail.CocktailDetailResponseDto
import com.cocktailsguru.app.cocktail.dto.list.CocktailListResponseDto
import com.cocktailsguru.app.cocktail.repository.CocktailRepository
import com.cocktailsguru.app.comment.dto.CommentListResponseDto
import com.cocktailsguru.app.utils.loggerFor
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.transaction.annotation.Transactional
import org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE
import org.springframework.web.context.WebApplicationContext
import kotlin.test.*


@RunWith(SpringRunner::class)
@SpringBootTest(classes = [(IntegrationTestApp::class)])
@Transactional
open class CocktailIntegrationPlayground {
    private val logger = loggerFor(javaClass)

    private lateinit var mockMvc: MockMvc

    @Autowired
    private lateinit var cocktailRepository: CocktailRepository

    @Autowired
    private lateinit var cocktailService: CocktailService

    private val objectMapper = jacksonObjectMapper()

    @Autowired
    lateinit var wac: WebApplicationContext

    @Before
    fun setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build()
        objectMapper.findAndRegisterModules()
    }


    @Test
    fun shouldFindFirstCocktailWithoutException() {
        assertNotNull(cocktailRepository.findOne(1L))
    }


    @Test
    fun shouldGetCocktailDetailWithoutPicturesOrComments() {
        val result = mockMvc.perform(
                get("/" + CocktailController.COCKTAIL_BASE_PATH + "/" + CocktailController.COCKTAIL_DETAIL_PATH)
                        .param("id", "54")
                        .contentType(APPLICATION_JSON_VALUE))
                .andExpect(status().isOk)
                .andReturn()
        val responseJson = result.response.contentAsString
        val responseDto = objectMapper.readValue(responseJson, CocktailDetailResponseDto::class.java)
        assertNotNull(responseDto)
        assertTrue(responseDto.pictureDtoList.list.isEmpty())
        assertTrue(responseDto.commentsDtoList.list.isEmpty())
        assertFalse(responseDto.objectDetail.ingredientList.isEmpty())
        assertFalse(responseDto.objectDetail.similarCocktailList.isEmpty())
    }

    @Test
    fun shouldGetCocktailDetailWithPicturesAndComments() {
        val result = mockMvc.perform(
                get("/" + CocktailController.COCKTAIL_BASE_PATH + "/" + CocktailController.COCKTAIL_DETAIL_PATH)
                        .param("id", "16000")
                        .param("commentsSize", "5")
                        .param("picturesSize", "5")
                        .contentType(APPLICATION_JSON_VALUE))
                .andExpect(status().isOk)
                .andReturn()
        val responseJson = result.response.contentAsString
        val responseDto = objectMapper.readValue(responseJson, CocktailDetailResponseDto::class.java)
        assertNotNull(responseDto)
        assertFalse(responseDto.pictureDtoList.list.isEmpty())
        assertFalse(responseDto.commentsDtoList.list.isEmpty())
        assertFalse(responseDto.objectDetail.ingredientList.isEmpty())
        assertFalse(responseDto.objectDetail.similarCocktailList.isEmpty())
    }

    @Test
    fun shouldReturnRequestedCocktailListSize() {
        val requestedPageNumber = 4
        val requestedPageSize = 12


        val result = mockMvc.perform(
                get("/" + CocktailController.COCKTAIL_BASE_PATH + "/" + CocktailController.COCKTAIL_LIST_PATH)
                        .param("pageNumber", requestedPageNumber.toString())
                        .param("pageSize", requestedPageSize.toString())
                        .contentType(APPLICATION_JSON_VALUE))
                .andExpect(status().isOk)
                .andReturn()
        val responseJson = result.response.contentAsString
        val responseDto = objectMapper.readValue(responseJson, CocktailListResponseDto::class.java)
        assertEquals(requestedPageSize, responseDto.list.size)
        assertEquals(requestedPageNumber, responseDto.pagingInfo.pageNumber)
        assertEquals(requestedPageSize, responseDto.pagingInfo.pageSize)
    }


    @Test
    fun whenRequestingEmptyCocktailListShouldReturnEmptyList() {
        val requestedPageNumber = 0
        val requestedPageSize = 0


        val result = mockMvc.perform(
                get("/" + CocktailController.COCKTAIL_BASE_PATH + "/" + CocktailController.COCKTAIL_LIST_PATH)
                        .param("pageNumber", requestedPageNumber.toString())
                        .param("pageSize", requestedPageSize.toString())
                        .contentType(APPLICATION_JSON_VALUE))
                .andExpect(status().isOk)
                .andReturn()
        val responseJson = result.response.contentAsString
        val responseDto = objectMapper.readValue(responseJson, CocktailListResponseDto::class.java)
        assertEquals(requestedPageSize, responseDto.list.size)
        assertEquals(requestedPageNumber, responseDto.pagingInfo.pageNumber)
        assertEquals(requestedPageSize, responseDto.pagingInfo.pageSize)
    }


    @Test
    fun shouldUpdateNumOfFavoritesForCocktailDetail() {
        assertNull(cocktailService.findCocktail(999999L))

        val margarita = cocktailService.findCocktail(54)

        assertNotNull(margarita)
        assertNotEquals(0, margarita!!.numOfFavorite.toLong())
    }

    @Test
    fun shouldReturnCommentListForCocktail() {
        val requestedPageNumber = 0
        val requestedPageSize = 10

        val result = mockMvc.perform(
                get("/" + CocktailController.COCKTAIL_BASE_PATH + "/" + CocktailController.COMMENT_LIST_PATH)
                        .param("id", "16000")
                        .param("pageNumber", requestedPageNumber.toString())
                        .param("pageSize", requestedPageSize.toString())
                        .contentType(APPLICATION_JSON_VALUE))
                .andExpect(status().isOk)
                .andReturn()
        val responseJson = result.response.contentAsString
        val responseDto = objectMapper.readValue(responseJson, CommentListResponseDto::class.java)
        assertFalse { responseDto.list.isEmpty() }
        assertEquals(requestedPageSize, responseDto.pagingInfo.pageSize)
        assertEquals(requestedPageNumber, responseDto.pagingInfo.pageNumber)
    }


    @Test
    fun whenRequestingEmptyCommentListShouldReturnEmptyList() {
        val requestedPageNumber = 0
        val requestedPageSize = 0

        val result = mockMvc.perform(
                get("/" + CocktailController.COCKTAIL_BASE_PATH + "/" + CocktailController.COMMENT_LIST_PATH)
                        .param("id", "16000")
                        .param("pageNumber", requestedPageNumber.toString())
                        .param("pageSize", requestedPageSize.toString())
                        .contentType(APPLICATION_JSON_VALUE))
                .andExpect(status().isOk)
                .andReturn()
        val responseJson = result.response.contentAsString
        val responseDto = objectMapper.readValue(responseJson, CommentListResponseDto::class.java)
        assertTrue { responseDto.list.isEmpty() }
        assertEquals(requestedPageSize, responseDto.pagingInfo.pageSize)
        assertEquals(requestedPageNumber, responseDto.pagingInfo.pageNumber)
    }
}