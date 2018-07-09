package com.cocktailsguru.app.ingredient.controller

import com.cocktailsguru.app.comment.domain.add.NewCommentRequest
import com.cocktailsguru.app.comment.dto.CommentListResponseDto
import com.cocktailsguru.app.comment.dto.NewCommentRequestDto
import com.cocktailsguru.app.comment.dto.NewCommentResponseDto
import com.cocktailsguru.app.common.domain.ObjectDetailRequest
import com.cocktailsguru.app.common.domain.PagingInfo
import com.cocktailsguru.app.common.dto.PagingDto
import com.cocktailsguru.app.ingredient.controller.IngredientController.Companion.INGREDIENT_BASE_PATH
import com.cocktailsguru.app.ingredient.domain.IngredientType
import com.cocktailsguru.app.ingredient.dto.detail.IngredientDetailDto
import com.cocktailsguru.app.ingredient.dto.detail.IngredientDetailResponseDto
import com.cocktailsguru.app.ingredient.dto.list.IngredientListItemDto
import com.cocktailsguru.app.ingredient.dto.list.IngredientListResponseDto
import com.cocktailsguru.app.ingredient.service.IngredientService
import com.cocktailsguru.app.picture.dto.PictureListResponseDto
import com.cocktailsguru.app.utils.loggerFor
import com.cocktailsguru.app.verification.service.UserVerificationService
import org.springframework.security.access.annotation.Secured
import org.springframework.web.bind.annotation.*

@RestController
@Secured(value = ["ROLE_MOBILE"])
@RequestMapping(INGREDIENT_BASE_PATH)
class IngredientController(
        private val ingredientService: IngredientService,
        private val userVerificationService: UserVerificationService
) {
    private val logger = loggerFor(javaClass)

    companion object {
        const val INGREDIENT_BASE_PATH = "ingredient"
        const val INGREDIENT_LIST_PATH = "list"
        const val INGREDIENT_DETAIL_PATH = "detail"
        const val COMMENT_LIST_PATH = "comments"
        const val PICTURE_LIST_PATH = "pictures"
        const val ADD_COMMENT_PATH = "addComment"
    }

    @RequestMapping(value = [(INGREDIENT_LIST_PATH)], produces = ["application/json"], method = [RequestMethod.GET])
    fun getIngredientList(
            @RequestParam("ingredientType", required = false) ingredientType: IngredientType?,
            @RequestParam("pageNumber") pageNumber: Int,
            @RequestParam("pageSize") pageSize: Int
    ): IngredientListResponseDto {
        logger.info("Requested ingredient list pageNumber {} page size {}", pageNumber, pageSize)
        val ingredientList = ingredientService.getIngredientList(ingredientType, PagingInfo(pageNumber, pageSize))
        return IngredientListResponseDto(
                ingredientList.objectList.map { IngredientListItemDto(it) },
                PagingDto(ingredientList.pagingInfo)
        )
    }

    @RequestMapping(value = [COMMENT_LIST_PATH], produces = ["application/json"], method = [RequestMethod.GET])
    fun getCommentList(
            @RequestParam("id") id: Long,
            @RequestParam("pageNumber") pageNumber: Int,
            @RequestParam("pageSize") pageSize: Int
    ): CommentListResponseDto {
        logger.info("Requested comment list for ingredient {} pageNumber {} page size {}", id, pageNumber, pageSize)
        val commentList = ingredientService.getCommentList(id, PagingInfo(pageNumber, pageSize))
        return CommentListResponseDto(commentList)
    }

    @RequestMapping(value = [(INGREDIENT_DETAIL_PATH)], produces = ["application/json"], method = [(RequestMethod.GET)])
    fun findIngredientDetail(
            @RequestParam("id") id: Long,
            @RequestParam("commentsSize", required = false, defaultValue = "0") commentsSize: Int
    ): IngredientDetailResponseDto? {
        logger.info("Request ingredient detail id {} commentsSize {}", id, commentsSize)
        val ingredientDetail = ingredientService.findIngredientDetail(ObjectDetailRequest(id, commentsSize))

        return ingredientDetail?.let {
            IngredientDetailResponseDto(
                    IngredientDetailDto(ingredientDetail.detail),
                    CommentListResponseDto(ingredientDetail.commentList)
            )
        }

    }

    @RequestMapping(value = [(ADD_COMMENT_PATH)], produces = ["application/json"], method = [RequestMethod.POST])
    @ResponseBody
    fun addComment(@RequestBody commentRequestDto: NewCommentRequestDto): NewCommentResponseDto {
        val ingredientId = commentRequestDto.objectId
        logger.info("Requested new comment for ingredient {}", ingredientId)
        val authorUser = userVerificationService.getLoggedUser()
        val newCommentRequest = NewCommentRequest(commentRequestDto.content, authorUser)
        val result = ingredientService.addNewComment(ingredientId, newCommentRequest)
        return NewCommentResponseDto(result)
    }

    @RequestMapping(value = [PICTURE_LIST_PATH], produces = ["application/json"], method = [RequestMethod.GET])
    fun getPictureList(
            @RequestParam("id") id: Long,
            @RequestParam("pageNumber") pageNumber: Int,
            @RequestParam("pageSize") pageSize: Int
    ): PictureListResponseDto {
        logger.info("Requested picture list for ingredient {} pageNumber {} page size {}", id, pageNumber, pageSize)
        val pictureList = ingredientService.getPicturesList(id, PagingInfo(pageNumber, pageSize))
        return PictureListResponseDto(pictureList)
    }
}