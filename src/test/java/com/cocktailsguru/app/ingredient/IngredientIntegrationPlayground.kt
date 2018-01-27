package com.cocktailsguru.app.ingredient

import com.cocktailsguru.app.IntegrationTestApp
import com.cocktailsguru.app.common.domain.PagingInfo
import com.cocktailsguru.app.common.dto.PagingDto
import com.cocktailsguru.app.ingredient.controller.IngredientController
import com.cocktailsguru.app.ingredient.dto.list.AlcoIngredientListResponseDto
import com.cocktailsguru.app.ingredient.dto.list.NonAlcoIngredientListResponseDto
import com.cocktailsguru.app.ingredient.service.IngredientService
import com.cocktailsguru.app.utils.loggerFor
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import junit.framework.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.test.web.servlet.MockMvc
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

    private val log = loggerFor(javaClass)

    private lateinit var mockMvc: MockMvc

    @Autowired
    lateinit var ingredientService: IngredientService

    @Autowired
    lateinit var wac: WebApplicationContext

    @Before
    fun setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build()
    }

    @Test
    fun shouldReturnAnyAlcoIngredientList() {
        val alcoIngredientList = ingredientService.getAlcoIngredientList(PagingInfo(0, 10))

        assertNotNull(alcoIngredientList)
        assertFalse(alcoIngredientList.list.isEmpty())
    }


    @Test
    fun shouldReturnAnyNonAlcoIngredientList() {
        val nonAlcoIngredientList = ingredientService.getNonAlcoIngredientList(PagingInfo(0, 10))

        assertNotNull(nonAlcoIngredientList)
        assertFalse(nonAlcoIngredientList.list.isEmpty())
    }


    @Test
    fun shouldReturnRequestedAlcoIngredientListSize() {
        val requestDto = PagingDto(0, 12)

        val objectMapper = jacksonObjectMapper()
        val ow = objectMapper.writer().withDefaultPrettyPrinter()
        val requestJson = ow.writeValueAsString(requestDto)


        val result = mockMvc.perform(
                post(IngredientController.ALCO_INGREDIENT_LIST_PATH)
                        .content(requestJson)
                        .contentType(APPLICATION_JSON_VALUE))
                .andExpect(status().isOk)
                .andReturn()
        val responseJson = result.response.contentAsString
        val responseDto = objectMapper.readValue(responseJson, AlcoIngredientListResponseDto::class.java)
        assertEquals(requestDto.pageSize.toLong(), responseDto.alcoIngredientList.size.toLong())
        assertEquals(requestDto, responseDto.pagingInfo)
    }


    @Test
    fun shouldReturnRequestedNonAlcoIngredientListSize() {
        val requestDto = PagingDto(0, 12)

        val objectMapper = jacksonObjectMapper()
        val ow = objectMapper.writer().withDefaultPrettyPrinter()
        val requestJson = ow.writeValueAsString(requestDto)


        val result = mockMvc.perform(
                post(IngredientController.NON_ALCO_INGREDIENT_LIST_PATH)
                        .content(requestJson)
                        .contentType(APPLICATION_JSON_VALUE))
                .andExpect(status().isOk)
                .andReturn()
        val responseJson = result.response.contentAsString
        val responseDto = objectMapper.readValue(responseJson, NonAlcoIngredientListResponseDto::class.java)
        assertEquals(requestDto.pageSize.toLong(), responseDto.nonAlcoIngredientList.size.toLong())
        assertEquals(requestDto, responseDto.pagingInfo)
    }
}