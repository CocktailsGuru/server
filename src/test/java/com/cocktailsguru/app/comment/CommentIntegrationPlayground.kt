package com.cocktailsguru.app.comment

import com.cocktailsguru.app.IntegrationTestApp
import com.cocktailsguru.app.cocktail.controller.CocktailController
import com.cocktailsguru.app.cocktail.domain.Cocktail
import com.cocktailsguru.app.cocktail.domain.CocktailObjectType
import com.cocktailsguru.app.comment.controller.CommentController
import com.cocktailsguru.app.comment.domain.add.NewCommentRequest
import com.cocktailsguru.app.comment.domain.add.NewCommentResultType
import com.cocktailsguru.app.comment.dto.CommentListResponseDto
import com.cocktailsguru.app.comment.dto.NewCommentRequestDto
import com.cocktailsguru.app.comment.dto.NewCommentResponseDto
import com.cocktailsguru.app.comment.service.CommentService
import com.cocktailsguru.app.common.domain.PagingInfo
import com.cocktailsguru.app.ingredient.controller.IngredientController
import com.cocktailsguru.app.ingredient.domain.Ingredient
import com.cocktailsguru.app.user.domain.UserTokenToVerify
import com.cocktailsguru.app.user.dto.UserTokenDto
import com.cocktailsguru.app.utils.loggerFor
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.nhaarman.mockito_kotlin.mock
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
import kotlin.test.assertNull
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


    @Test
    fun whenRequestingNewCommentForNonExistingCocktailShouldReturnErrorResponse() {
        val margaritaId = -1L
        val content = "anyContent"
        val userTokenDto = getAdminUserTokenDto()

        val requestDto = objectMapper.writer().writeValueAsString(NewCommentRequestDto(
                userTokenDto,
                margaritaId,
                content
        ))

        val result = mockMvc.perform(
                MockMvcRequestBuilders.post("/" + CocktailController.COCKTAIL_BASE_PATH + "/" + CocktailController.ADD_COMMENT_PATH)
                        .content(requestDto)
                        .contentType(MimeTypeUtils.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk)
                .andReturn()

        val responseJson = result.response.contentAsString
        val responseDto = objectMapper.readValue(responseJson, NewCommentResponseDto::class.java)
        assertEquals(NewCommentResultType.OBJECT_NOT_FOUND, responseDto.resultType)
    }

    @Test
    fun whenRequestingNewCommentForNonExistingCocktailViaWebShouldReturnUnauthorized() {
        val margaritaId = 54L
        val content = "anyContent"
        val userTokenDto = UserTokenDto(-1, "notAToken")

        val requestDto = objectMapper.writer().writeValueAsString(NewCommentRequestDto(
                userTokenDto,
                margaritaId,
                content
        ))

        mockMvc.perform(
                MockMvcRequestBuilders.post("/" + CocktailController.COCKTAIL_BASE_PATH + "/" + CocktailController.ADD_COMMENT_PATH)
                        .content(requestDto)
                        .contentType(MimeTypeUtils.APPLICATION_JSON_VALUE))
                .andExpect(status().isUnauthorized)
                .andReturn()
    }

    @Test
    fun whenRequestingNewCommentForCocktailForNonExistingUserShouldReturnErrorResponse() {
        val newCommentRequest = NewCommentRequest(UserTokenToVerify(-1, ""), "anyContent")
        val anyCocktail = mock<Cocktail>()
        val result = commentService.addNewComment(anyCocktail, newCommentRequest)
        assertEquals(NewCommentResultType.USER_NOT_FOUND, result.resultType)
        assertNull(result.comment)
    }


    @Test
    fun whenRequestingNewCommentForCocktailShouldAddComment() {
        val margaritaId = 54L
        val content = "anyContent"
        val userTokenDto = getAdminUserTokenDto()

        val requestDto = objectMapper.writer().writeValueAsString(NewCommentRequestDto(
                userTokenDto,
                margaritaId,
                content
        ))

        val result = mockMvc.perform(
                MockMvcRequestBuilders.post("/" + CocktailController.COCKTAIL_BASE_PATH + "/" + CocktailController.ADD_COMMENT_PATH)
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
        assertTrue { newComment.isVisible }
    }


    @Test
    fun whenRequestingNewCommentForNonExistingIngredientShouldReturnErrorResponse() {
        val nonExistingIngredient = -1L
        val content = "anyContent"
        val userTokenDto = getAdminUserTokenDto()

        val requestDto = objectMapper.writer().writeValueAsString(NewCommentRequestDto(
                userTokenDto,
                nonExistingIngredient,
                content
        ))

        val result = mockMvc.perform(
                MockMvcRequestBuilders.post("/" + IngredientController.INGREDIENT_BASE_PATH + "/" + CocktailController.ADD_COMMENT_PATH)
                        .content(requestDto)
                        .contentType(MimeTypeUtils.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk)
                .andReturn()

        val responseJson = result.response.contentAsString
        val responseDto = objectMapper.readValue(responseJson, NewCommentResponseDto::class.java)
        assertEquals(NewCommentResultType.OBJECT_NOT_FOUND, responseDto.resultType)
    }

    @Test
    fun whenRequestingNewCommentForNonExistingIngredientViaWebShouldReturnUnauthorized() {
        val ingredientId = 1L
        val content = "anyContent"
        val userTokenDto = UserTokenDto(-1, "notAToken")

        val requestDto = objectMapper.writer().writeValueAsString(NewCommentRequestDto(
                userTokenDto,
                ingredientId,
                content
        ))

        mockMvc.perform(
                MockMvcRequestBuilders.post("/" + IngredientController.INGREDIENT_BASE_PATH + "/" + CocktailController.ADD_COMMENT_PATH)
                        .content(requestDto)
                        .contentType(MimeTypeUtils.APPLICATION_JSON_VALUE))
                .andExpect(status().isUnauthorized)
                .andReturn()
    }

    @Test
    fun whenRequestingNewCommentForIngredientForNonExistingUserShouldReturnErrorResponse() {
        val newCommentRequest = NewCommentRequest(UserTokenToVerify(-1, ""), "anyContent")
        val anyIngredient = mock<Ingredient>()
        val result = commentService.addNewComment(anyIngredient, newCommentRequest)
        assertEquals(NewCommentResultType.USER_NOT_FOUND, result.resultType)
        assertNull(result.comment)
    }


    @Test
    fun whenRequestingNewCommentForIngredientShouldAddComment() {
        val ingredientId = 1L
        val content = "anyContent"
        val userTokenDto = getAdminUserTokenDto()

        val requestDto = objectMapper.writer().writeValueAsString(NewCommentRequestDto(
                userTokenDto,
                ingredientId,
                content
        ))

        val result = mockMvc.perform(
                MockMvcRequestBuilders.post("/" + IngredientController.INGREDIENT_BASE_PATH + "/" + CocktailController.ADD_COMMENT_PATH)
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
        assertTrue { newComment.isVisible }
    }

    private fun getAdminUserTokenDto() = UserTokenDto(6L, "adminToken")
}