package com.cocktailsguru.app.ingredient

import com.cocktailsguru.app.IntegrationTestApp
import com.cocktailsguru.app.common.domain.PagingInfo
import com.cocktailsguru.app.common.dto.ListResponseDto
import com.cocktailsguru.app.common.dto.PagingDto
import com.cocktailsguru.app.ingredient.controller.IngredientController
import com.cocktailsguru.app.ingredient.domain.IngredientType
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
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.transaction.annotation.Transactional
import org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE
import org.springframework.web.context.WebApplicationContext
import kotlin.test.assertFalse
import kotlin.test.assertNotNull


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

    @Before
    fun setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build()
    }

    @Test
    fun shouldReturnAnyIngredientList() {
        val ingredientList = ingredientService.getIngredientList(PagingInfo(0, 10))

        assertNotNull(ingredientList)
        assertFalse(ingredientList.list.isEmpty())
    }

    @Test
    fun shouldReturnRequestedIngredientListSize() {
        val requestDto = PagingDto(0, 12)

        val objectMapper = jacksonObjectMapper()
        val ow = objectMapper.writer().withDefaultPrettyPrinter()
        val requestJson = ow.writeValueAsString(requestDto)


        val result = mockMvc.perform(
                post(IngredientController.INGREDIENT_LIST_PATH)
                        .content(requestJson)
                        .contentType(APPLICATION_JSON_VALUE))
                .andExpect(status().isOk)
                .andReturn()
        val responseJson = result.response.contentAsString
        val responseDto = objectMapper.readValue(responseJson, ListResponseDto::class.java)
        assertEquals(requestDto.pageSize.toLong(), responseDto.list.size.toLong())
        assertEquals(requestDto, responseDto.pagingInfo)
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
        val one = ingredientCategoryTypeRepository.findOne(1)
        assertNotNull(one)
    }

    @Test
    fun shouldWebFindIngredientDetail() {
        val result = mockMvc.perform(
                get(IngredientController.INGREDIENT_DETAIL_PATH)
                        .param("id", "1")
                        .contentType(APPLICATION_JSON_VALUE))
                .andExpect(status().isOk)
                .andReturn()
        val contentAsString = result.response.contentAsString
        assertNotNull(contentAsString)
    }

    @Test
    fun shouldFindIngredientWithoutException() {
        assertNotNull(ingredientRepository.findOne(1L))
    }
}