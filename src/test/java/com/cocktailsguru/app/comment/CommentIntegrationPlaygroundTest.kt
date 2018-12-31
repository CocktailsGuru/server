package com.cocktailsguru.app.comment

import com.cocktailsguru.app.MockRequestUtils
import com.cocktailsguru.app.cocktail.controller.CocktailController
import com.cocktailsguru.app.cocktail.domain.CocktailObjectType
import com.cocktailsguru.app.comment.controller.CommentController
import com.cocktailsguru.app.comment.domain.add.NewCommentResultType
import com.cocktailsguru.app.comment.dto.CommentListResponseDto
import com.cocktailsguru.app.comment.dto.NewCommentRequestDto
import com.cocktailsguru.app.comment.dto.NewCommentResponseDto
import com.cocktailsguru.app.comment.service.CommentService
import com.cocktailsguru.app.common.domain.PagingInfo
import com.cocktailsguru.app.ingredient.controller.IngredientController
import com.cocktailsguru.app.user.dto.UserTokenDto
import com.cocktailsguru.app.utils.loggerFor
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import junit.framework.TestCase.*
import org.junit.Before
import org.junit.Ignore
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


@RunWith(SpringRunner::class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Transactional
class CommentIntegrationPlaygroundTest {
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
    @Ignore
    fun shouldFindCommentForMargarita() {
        val margaritaCommentList = commentService.getCommentListForObject(CocktailObjectType.COCKTAIL, 54, PagingInfo(0, 10))
        margaritaCommentList.objectList.forEach {
            logger.info(it.authorUser.toString())
        }
        assertFalse(margaritaCommentList.objectList.isEmpty())
    }


    @Test
    @Ignore
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
        assertFalse(responseDto.list.isEmpty())
        assertTrue(requestedPageSize >= responseDto.list.size)
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
        assertTrue(responseDto.list.isEmpty())
        assertEquals(requestedPageNumber, responseDto.pagingInfo.pageNumber)
        assertEquals(requestedPageSize, responseDto.pagingInfo.pageSize)
    }


    @Test
    @Ignore
    fun whenRequestingNewCommentForNonExistingCocktailShouldReturnErrorResponse() {
        val margaritaId = -1L
        val content = "anyContent"

        val requestDto = objectMapper.writer().writeValueAsString(NewCommentRequestDto(
                margaritaId,
                content
        ))

        val result = mockMvc.perform(
                MockRequestUtils.addAdminHeaders(MockMvcRequestBuilders.post("/" + CocktailController.COCKTAIL_BASE_PATH + "/" + CocktailController.ADD_COMMENT_PATH))
                        .content(requestDto)
                        .contentType(MimeTypeUtils.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk)
                .andReturn()

        val responseJson = result.response.contentAsString
        val responseDto = objectMapper.readValue(responseJson, NewCommentResponseDto::class.java)
        assertEquals(NewCommentResultType.OBJECT_NOT_FOUND, responseDto.resultType)
    }

    @Test
    @Ignore
    fun whenRequestingNewCommentForCocktailShouldAddComment() {
        val margaritaId = 54L
        val content = "anyContent"
        val userTokenDto = getAdminUserTokenDto()

        val requestDto = objectMapper.writer().writeValueAsString(NewCommentRequestDto(
                margaritaId,
                content
        ))

        val result = mockMvc.perform(
                MockRequestUtils.addAdminHeaders(MockMvcRequestBuilders.post("/" + CocktailController.COCKTAIL_BASE_PATH + "/" + CocktailController.ADD_COMMENT_PATH))
                        .content(requestDto)
                        .contentType(MimeTypeUtils.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk)
                .andReturn()

        val responseJson = result.response.contentAsString
        val responseDto = objectMapper.readValue(responseJson, NewCommentResponseDto::class.java)
        assertEquals(NewCommentResultType.OK, responseDto.resultType)


        val commentList = commentService.getCommentListForObject(CocktailObjectType.COCKTAIL, margaritaId, PagingInfo(0, 50))
        val newComment = commentList.objectList.last()
        assertEquals(userTokenDto.userId, newComment.authorUser.id)
        assertEquals(CocktailObjectType.COCKTAIL, newComment.objectType)
        assertEquals(margaritaId, newComment.objectForeignKey)
        assertEquals(0, newComment.numDislikes)
        assertEquals(0, newComment.numLikes)
        assertTrue(newComment.isVisible)
    }


    @Test
    fun whenRequestingNewCommentForNonExistingIngredientShouldReturnErrorResponse() {
        val nonExistingIngredient = -1L
        val content = "anyContent"

        val requestDto = objectMapper.writer().writeValueAsString(NewCommentRequestDto(
                nonExistingIngredient,
                content
        ))

        val result = mockMvc.perform(
                MockRequestUtils.addAdminHeaders(MockMvcRequestBuilders.post("/" + IngredientController.INGREDIENT_BASE_PATH + "/" + CocktailController.ADD_COMMENT_PATH))
                        .content(requestDto)
                        .contentType(MimeTypeUtils.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk)
                .andReturn()

        val responseJson = result.response.contentAsString
        val responseDto = objectMapper.readValue(responseJson, NewCommentResponseDto::class.java)
        assertEquals(NewCommentResultType.OBJECT_NOT_FOUND, responseDto.resultType)
    }

    @Test
    fun whenRequestingNewCommentForNonExistingIngredientViaWebShouldReturnNotFoundCode() {
        val ingredientId = -1L
        val content = "anyContent"

        val requestDto = objectMapper.writer().writeValueAsString(NewCommentRequestDto(
                ingredientId,
                content
        ))

        val result = mockMvc.perform(
                MockRequestUtils.addAdminHeaders(MockMvcRequestBuilders.post("/" + IngredientController.INGREDIENT_BASE_PATH + "/" + CocktailController.ADD_COMMENT_PATH))
                        .content(requestDto)
                        .contentType(MimeTypeUtils.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk)
                .andReturn()

        val responseJson = result.response.contentAsString
        val responseDto = objectMapper.readValue(responseJson, NewCommentResponseDto::class.java)
        assertEquals(NewCommentResultType.OBJECT_NOT_FOUND, responseDto.resultType)
    }


    @Test
    @Ignore
    fun whenRequestingNewCommentForIngredientShouldAddComment() {
        val ingredientId = 1L
        val content = "anyContent"
        val userTokenDto = getAdminUserTokenDto()

        val requestDto = objectMapper.writer().writeValueAsString(NewCommentRequestDto(
                ingredientId,
                content
        ))

        val result = mockMvc.perform(
                MockRequestUtils.addAdminHeaders(MockMvcRequestBuilders.post("/" + IngredientController.INGREDIENT_BASE_PATH + "/" + CocktailController.ADD_COMMENT_PATH))
                        .content(requestDto)
                        .contentType(MimeTypeUtils.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk)
                .andReturn()

        val responseJson = result.response.contentAsString
        val responseDto = objectMapper.readValue(responseJson, NewCommentResponseDto::class.java)
        assertEquals(NewCommentResultType.OK, responseDto.resultType)


        val commentList = commentService.getCommentListForObject(CocktailObjectType.INGREDIENT, ingredientId, PagingInfo(0, 50))
        val newComment = commentList.objectList.last()
        assertEquals(userTokenDto.userId, newComment.authorUser.id)
        assertEquals(CocktailObjectType.INGREDIENT, newComment.objectType)
        assertEquals(ingredientId, newComment.objectForeignKey)
        assertEquals(0, newComment.numDislikes)
        assertEquals(0, newComment.numLikes)
        assertTrue(newComment.isVisible)
    }

    private fun getAdminUserTokenDto() = UserTokenDto(6L, "adminToken")
}
