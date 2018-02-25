package com.cocktailsguru.app.cocktail.service

import com.cocktailsguru.app.IntegrationTestApp
import com.cocktailsguru.app.cocktail.controller.CocktailController
import com.cocktailsguru.app.cocktail.dto.CocktailDetailDto
import com.cocktailsguru.app.cocktail.dto.list.CocktailListResponseDto
import com.cocktailsguru.app.cocktail.repository.CocktailRepository
import com.cocktailsguru.app.common.dto.PagingDto
import com.cocktailsguru.app.utils.loggerFor
import com.fasterxml.jackson.databind.ObjectMapper
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.transaction.annotation.Transactional
import org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE
import org.springframework.web.context.WebApplicationContext
import kotlin.test.assertFalse
import kotlin.test.assertNotEquals
import kotlin.test.assertNotNull
import kotlin.test.assertNull


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

    private val objectMapper = ObjectMapper()
    private val objectWriter = objectMapper.writer().withDefaultPrettyPrinter()

    @Autowired
    lateinit var wac: WebApplicationContext

    @Before
    fun setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build()
    }


    @Test
    fun shouldFindFirstCocktailWithouException() {
        assertNotNull(cocktailRepository.findOne(1L))
    }


    @Test
    @Throws(Exception::class)
    fun shouldGetCocktailDetail() {
        val result = mockMvc.perform(
                get("/" + CocktailController.COCKTAIL_BASE_PATH + "/" + CocktailController.COCKTAIL_DETAIL_PATH)
                        .param("id", "1")
                        .contentType(APPLICATION_JSON_VALUE))
                .andExpect(status().isOk)
                .andReturn()
        val responseJson = result.response.contentAsString
        val responseDto = objectMapper.readValue(responseJson, CocktailDetailDto::class.java)
        assertNotNull(responseDto)
        assertFalse(responseDto.ingredientList.isEmpty())
        assertFalse(responseDto.similarCocktailList.isEmpty())

        logger.info(responseJson)
    }

    @Test
    @Throws(Exception::class)
    fun shouldReturnRequestedCocktailListSize() {
        val requestDto = PagingDto(4, 12)

        val requestJson = objectWriter.writeValueAsString(requestDto)


        val result = mockMvc.perform(
                post("/" + CocktailController.COCKTAIL_BASE_PATH + "/" + CocktailController.COCKTAIL_LIST_PATH)
                        .content(requestJson)
                        .contentType(APPLICATION_JSON_VALUE))
                .andExpect(status().isOk)
                .andReturn()
        val responseJson = result.response.contentAsString
        val responseDto = objectMapper.readValue(responseJson, CocktailListResponseDto::class.java)
        assertEquals(requestDto.pageSize.toLong(), responseDto.list.size.toLong())
        assertEquals(requestDto, responseDto.pagingInfo)
    }


    @Test
    fun shouldUpdateNumOfFavoritesForCocktailDetail() {
        assertNull(cocktailService.getCocktailDetail(999999L))

        val margarita = cocktailService.getCocktailDetail(54)

        assertNotNull(margarita)
        assertNotEquals(0, margarita!!.numOfFavorite.toLong())
    }
}