package com.cocktailsguru.app.comment

import com.cocktailsguru.app.IntegrationTestApp
import com.cocktailsguru.app.cocktail.domain.CocktailObjectType
import com.cocktailsguru.app.comment.controller.CommentController
import com.cocktailsguru.app.comment.service.CommentService
import com.cocktailsguru.app.utils.loggerFor
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.json.JacksonJsonParser
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.transaction.annotation.Transactional
import org.springframework.util.MimeTypeUtils
import org.springframework.web.context.WebApplicationContext
import kotlin.test.assertFalse


@RunWith(SpringRunner::class)
@SpringBootTest(classes = [(IntegrationTestApp::class)])
@Transactional
open class CommentIntegrationPlayground {
    private val logger = loggerFor(javaClass)

    private lateinit var mockMvc: MockMvc

    @Autowired
    private lateinit var commentService: CommentService

    @Autowired
    private lateinit var wac: WebApplicationContext

    @Before
    fun setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build()
    }

    @Test
    fun shouldFindCommentForMargarita() {
        val margaritaCommentList = commentService.getCommentListForObject(CocktailObjectType.COCKTAIL, 54)
        margaritaCommentList.forEach {
            logger.info(it.authorUser.toString())
        }
        assertFalse(margaritaCommentList.isEmpty())
    }


    @Test
    fun shouldReturnNonEmptyCommentListForMargarita() {

        val result = mockMvc.perform(
                MockMvcRequestBuilders.get(CommentController.COMMENT_LIST_PATH)
                        .param("objectId", "54")
                        .param("objectType", "1")
                        .contentType(MimeTypeUtils.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk)
                .andReturn()
        val responseJson = result.response.contentAsString
        val responseDto = JacksonJsonParser().parseList(responseJson)
        logger.info(responseDto.toString())
        assertFalse { responseDto.isEmpty() }
    }
}