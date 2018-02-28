package com.cocktailsguru.app.comment

import com.cocktailsguru.app.IntegrationTestApp
import com.cocktailsguru.app.cocktail.domain.CocktailObjectType
import com.cocktailsguru.app.comment.controller.CommentController
import com.cocktailsguru.app.comment.dto.CommentListResponseDto
import com.cocktailsguru.app.comment.service.CommentService
import com.cocktailsguru.app.common.domain.PagingInfo
import com.cocktailsguru.app.utils.loggerFor
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.transaction.annotation.Transactional
import org.springframework.util.MimeTypeUtils
import org.springframework.web.context.WebApplicationContext
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue


@RunWith(SpringRunner::class)
@SpringBootTest(classes = [(IntegrationTestApp::class)])
@Transactional
open class CommentIntegrationPlayground {
    private val logger = loggerFor(javaClass)

    private lateinit var mockMvc: MockMvc

    private val objectMapper = jacksonObjectMapper()

    @Autowired
    private lateinit var commentService: CommentService

    @Autowired
    private lateinit var wac: WebApplicationContext

    @Before
    fun setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build()
        objectMapper.findAndRegisterModules()
    }

    @Test
    fun shouldFindCommentForMargarita() {
        val margaritaCommentList = commentService.getCommentListForObject(CocktailObjectType.COCKTAIL, 54, PagingInfo(0, 10))
        margaritaCommentList.objectList.forEach {
            logger.info(it.authorUser.toString())
        }
        assertFalse(margaritaCommentList.objectList.isEmpty())
    }


    @Test
    fun shouldReturnNonEmptyCommentListForMargarita() {
        val requestedPageNumber = 0
        val requestedPageSize = 12

        val result = mockMvc.perform(
                MockMvcRequestBuilders.get("/" + CommentController.COMMENT_BASE_PATH + "/" + CommentController.COMMENT_LIST_PATH)
                        .param("objectId", "54")
                        .param("objectType", "1")
                        .param("pageNumber", requestedPageNumber.toString())
                        .param("pageSize", requestedPageSize.toString())
                        .contentType(MimeTypeUtils.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk)
                .andReturn()
        val responseJson = result.response.contentAsString
        val responseDto = objectMapper.readValue(responseJson, CommentListResponseDto::class.java)
        logger.debug(responseDto.toString())
        assertFalse { responseDto.list.isEmpty() }
        assertTrue { requestedPageSize >= responseDto.list.size }
        assertEquals(requestedPageNumber, responseDto.pagingInfo.pageNumber)
        assertEquals(requestedPageSize, responseDto.pagingInfo.pageSize)
    }


    @Test
    fun whenRequestingEmptyListShouldReturnEmptyList() {
        val requestedPageNumber = 0
        val requestedPageSize = 0

        val result = mockMvc.perform(
                MockMvcRequestBuilders.get("/" + CommentController.COMMENT_BASE_PATH + "/" + CommentController.COMMENT_LIST_PATH)
                        .param("objectId", "54")
                        .param("objectType", "1")
                        .param("pageNumber", requestedPageNumber.toString())
                        .param("pageSize", requestedPageSize.toString())
                        .contentType(MimeTypeUtils.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk)
                .andReturn()
        val responseJson = result.response.contentAsString
        val responseDto = objectMapper.readValue(responseJson, CommentListResponseDto::class.java)
        logger.debug(responseDto.toString())
        assertTrue { responseDto.list.isEmpty() }
        assertEquals(requestedPageNumber, responseDto.pagingInfo.pageNumber)
        assertEquals(requestedPageSize, responseDto.pagingInfo.pageSize)
    }
}