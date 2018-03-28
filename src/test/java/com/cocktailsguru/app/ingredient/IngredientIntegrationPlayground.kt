package com.cocktailsguru.app.ingredient

import com.cocktailsguru.app.IntegrationTestApp
import com.cocktailsguru.app.comment.dto.CommentListResponseDto
import com.cocktailsguru.app.common.domain.PagingInfo
import com.cocktailsguru.app.ingredient.controller.IngredientController
import com.cocktailsguru.app.ingredient.domain.IngredientType
import com.cocktailsguru.app.ingredient.dto.detail.IngredientDetailResponseDto
import com.cocktailsguru.app.ingredient.dto.list.IngredientListResponseDto
import com.cocktailsguru.app.ingredient.repository.IngredientCategoryTypeRepository
import com.cocktailsguru.app.ingredient.repository.IngredientRepository
import com.cocktailsguru.app.ingredient.service.IngredientService
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
import kotlin.test.assertFalse
import kotlin.test.assertNotNull
import kotlin.test.assertTrue


@RunWith(SpringRunner::class)
@SpringBootTest(classes = [(IntegrationTestApp::class)])
@Transactional
open class IngredientIntegrationPlayground {
    private lateinit var mockMvc: MockMvc

    @Autowired
    lateinit var ingredientService: IngredientService

    @Autowired
    lateinit var ingredientCategoryTypeRepository: IngredientCategoryTypeRepository

    @Autowired
    lateinit var ingredientRepository: IngredientRepository

    @Autowired
    lateinit var wac: WebApplicationContext

    private val objectMapper = jacksonObjectMapper()

    @Before
    fun setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build()
        objectMapper.findAndRegisterModules()
    }

    @Test
    fun shouldReturnAnyIngredientList() {
        val ingredientList = ingredientService.getIngredientList(null, PagingInfo(0, 10))

        assertNotNull(ingredientList)
        assertFalse(ingredientList.objectList.isEmpty())
    }

    @Test
    fun shouldReturnNonAlcoIngredientList() {
        val ingredientList = ingredientService.getIngredientList(IngredientType.NON_ALCO, PagingInfo(0, 10))

        assertNotNull(ingredientList)
        assertFalse(ingredientList.objectList.isEmpty())
        assertEquals(10, ingredientList.objectList.size)
        ingredientList.objectList.forEach { assertEquals(IngredientType.NON_ALCO, it.ingredientType) }
    }


    @Test
    fun shouldReturnAlcoIngredientList() {
        val ingredientList = ingredientService.getIngredientList(IngredientType.ALCO, PagingInfo(0, 10))

        assertNotNull(ingredientList)
        assertFalse(ingredientList.objectList.isEmpty())
        assertEquals(10, ingredientList.objectList.size)
        ingredientList.objectList.forEach { assertEquals(IngredientType.ALCO, it.ingredientType) }
    }

    @Test
    fun shouldReturnRequestedIngredientListSize() {
        val requestedPageNumber = 0
        val requestedPageSize = 12

        val result = mockMvc.perform(
                get("/" + IngredientController.INGREDIENT_BASE_PATH + "/" + IngredientController.INGREDIENT_LIST_PATH)
                        .param("pageNumber", requestedPageNumber.toString())
                        .param("pageSize", requestedPageSize.toString())
                        .contentType(APPLICATION_JSON_VALUE))
                .andExpect(status().isOk)
                .andReturn()
        val responseJson = result.response.contentAsString
        val responseDto = objectMapper.readValue(responseJson, IngredientListResponseDto::class.java)
        assertEquals(requestedPageSize, responseDto.list.size)
        assertEquals(requestedPageSize, responseDto.pagingInfo.pageSize)
        assertEquals(requestedPageNumber, responseDto.pagingInfo.pageNumber)
    }

    @Test
    fun whenRequestingEmptyIngredientListShouldReturnEmptyList() {
        val requestedPageNumber = 0
        val requestedPageSize = 0

        val result = mockMvc.perform(
                get("/" + IngredientController.INGREDIENT_BASE_PATH + "/" + IngredientController.INGREDIENT_LIST_PATH)
                        .param("pageNumber", requestedPageNumber.toString())
                        .param("pageSize", requestedPageSize.toString())
                        .contentType(APPLICATION_JSON_VALUE))
                .andExpect(status().isOk)
                .andReturn()
        val responseJson = result.response.contentAsString
        val responseDto = objectMapper.readValue(responseJson, IngredientListResponseDto::class.java)
        assertTrue { responseDto.list.isEmpty() }
        assertEquals(requestedPageSize, responseDto.pagingInfo.pageSize)
        assertEquals(requestedPageNumber, responseDto.pagingInfo.pageNumber)
    }

    @Test
    fun shouldFindNonAlcoIngredient() {
        val foundIngredient = ingredientService.findIngredient(1168)
        assertNotNull(foundIngredient)
        assertEquals(IngredientType.NON_ALCO, foundIngredient!!.ingredientType)
    }

    @Test
    fun shouldFindAlcoIngredient() {
        val alcoIngredient = ingredientService.findIngredient(1)
        assertNotNull(alcoIngredient)
        assertEquals(IngredientType.ALCO, alcoIngredient!!.ingredientType)
    }

    @Test
    fun shouldFindFirstIngredientTypeWithoutException() {
        assertNotNull(ingredientCategoryTypeRepository.findOne(1))
    }

    @Test
    fun shouldWebFindIngredientDetailWithoutRequestedComments() {
        val result = mockMvc.perform(
                get("/" + IngredientController.INGREDIENT_BASE_PATH + "/" + IngredientController.INGREDIENT_DETAIL_PATH)
                        .param("id", "1")
                        .contentType(APPLICATION_JSON_VALUE))
                .andExpect(status().isOk)
                .andReturn()
        val contentAsString = result.response.contentAsString
        assertNotNull(contentAsString)
        val responseDto = objectMapper.readValue(contentAsString, IngredientDetailResponseDto::class.java)
        assertTrue { responseDto.commentsDtoList.list.isEmpty() }
        assertEquals(0, responseDto.commentsDtoList.pagingInfo.pageSize)
        assertEquals(0, responseDto.commentsDtoList.pagingInfo.pageNumber)
    }

    @Test
    fun shouldWebFindIngredientDetailWithRequestedComments() {
        val requestedCommentsSize = 10
        val result = mockMvc.perform(
                get("/" + IngredientController.INGREDIENT_BASE_PATH + "/" + IngredientController.INGREDIENT_DETAIL_PATH)
                        .param("id", "3")
                        .param("commentsSize", requestedCommentsSize.toString())
                        .contentType(APPLICATION_JSON_VALUE))
                .andExpect(status().isOk)
                .andReturn()
        val contentAsString = result.response.contentAsString
        assertNotNull(contentAsString)
        val responseDto = objectMapper.readValue(contentAsString, IngredientDetailResponseDto::class.java)
        assertFalse { responseDto.commentsDtoList.list.isEmpty() }
        assertEquals(requestedCommentsSize, responseDto.commentsDtoList.list.size)
        assertEquals(requestedCommentsSize, responseDto.commentsDtoList.pagingInfo.pageSize)
        assertEquals(0, responseDto.commentsDtoList.pagingInfo.pageNumber)
    }

    @Test
    fun shouldFindIngredientWithoutException() {
        assertNotNull(ingredientRepository.findOne(1L))
    }


    @Test
    fun shouldReturnCommentListForIngredient() {
        val requestedPageNumber = 0
        val requestedPageSize = 10

        val result = mockMvc.perform(
                get("/" + IngredientController.INGREDIENT_BASE_PATH + "/" + IngredientController.COMMENT_LIST_PATH)
                        .param("id", "3")
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
                get("/" + IngredientController.INGREDIENT_BASE_PATH + "/" + IngredientController.COMMENT_LIST_PATH)
                        .param("id", "3")
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