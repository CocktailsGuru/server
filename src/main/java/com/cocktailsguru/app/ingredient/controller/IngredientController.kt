package com.cocktailsguru.app.ingredient.controller

import com.cocktailsguru.app.comment.dto.CommentListResponseDto
import com.cocktailsguru.app.common.domain.ObjectDetailRequest
import com.cocktailsguru.app.common.domain.PagingInfo
import com.cocktailsguru.app.common.dto.PagingDto
import com.cocktailsguru.app.ingredient.controller.IngredientController.Companion.INGREDIENT_BASE_PATH
import com.cocktailsguru.app.ingredient.dto.detail.IngredientDetailDto
import com.cocktailsguru.app.ingredient.dto.detail.IngredientDetailResponseDto
import com.cocktailsguru.app.ingredient.dto.list.IngredientListItemDto
import com.cocktailsguru.app.ingredient.dto.list.IngredientListResponseDto
import com.cocktailsguru.app.ingredient.service.IngredientService
import com.cocktailsguru.app.utils.loggerFor
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.access.annotation.Secured
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@Secured(value = ["ROLE_MOBILE"])
@RequestMapping(INGREDIENT_BASE_PATH)
open class IngredientController @Autowired constructor(
        private val ingredientService: IngredientService
) {
    private val logger = loggerFor(javaClass)

    companion object {
        const val INGREDIENT_BASE_PATH = "ingredient"
        const val INGREDIENT_LIST_PATH = "list"
        const val INGREDIENT_DETAIL_PATH = "detail"
        const val COMMENT_LIST_PATH = "comments"
    }

    @RequestMapping(value = [(INGREDIENT_LIST_PATH)], produces = ["application/json"], method = [RequestMethod.GET])
    open fun getIngredientList(
            @RequestParam("pageNumber") pageNumber: Int,
            @RequestParam("pageSize") pageSize: Int
    ): IngredientListResponseDto {
        logger.info("Requested ingredient list pageNumber {} page size {}", pageNumber, pageSize)
        val ingredientList = ingredientService.getIngredientList(PagingInfo(pageNumber, pageSize))
        return IngredientListResponseDto(
                ingredientList.objectList.map { IngredientListItemDto(it) },
                PagingDto(ingredientList.pagingInfo)
        )
    }

    @RequestMapping(value = [COMMENT_LIST_PATH], produces = ["application/json"], method = [RequestMethod.GET])
    open fun getCommentList(
            @RequestParam("id") id: Long,
            @RequestParam("pageNumber") pageNumber: Int,
            @RequestParam("pageSize") pageSize: Int
    ): CommentListResponseDto {
        logger.info("Requested comment list for ingredient {} pageNumber {} page size {}", id, pageNumber, pageSize)
        val commentList = ingredientService.getCommentList(id, PagingInfo(pageNumber, pageSize))
        return CommentListResponseDto(commentList)
    }

    @RequestMapping(value = [(INGREDIENT_DETAIL_PATH)], produces = ["application/json"], method = [(RequestMethod.GET)])
    open fun findIngredientDetail(
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
}