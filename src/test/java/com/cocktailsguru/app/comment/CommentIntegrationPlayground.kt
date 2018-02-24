package com.cocktailsguru.app.comment

import com.cocktailsguru.app.IntegrationTestApp
import com.cocktailsguru.app.cocktail.domain.CocktailObjectType
import com.cocktailsguru.app.comment.service.CommentService
import com.cocktailsguru.app.utils.loggerFor
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.transaction.annotation.Transactional
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
}