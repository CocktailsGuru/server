package com.cocktailsguru.app.comment.controller

import com.cocktailsguru.app.cocktail.domain.CocktailObjectType
import com.cocktailsguru.app.comment.controller.CommentController.Companion.COMMENT_BASE_PATH
import com.cocktailsguru.app.comment.dto.CommentListResponseDto
import com.cocktailsguru.app.comment.service.CommentService
import com.cocktailsguru.app.common.domain.PagingInfo
import com.cocktailsguru.app.utils.loggerFor
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.access.annotation.Secured
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@Secured(value = ["ROLE_MOBILE"])
@RequestMapping(COMMENT_BASE_PATH)
class CommentController @Autowired constructor(
        private val commentService: CommentService
) {

    private val logger = loggerFor(javaClass)

    companion object {
        const val COMMENT_BASE_PATH = "comment"
        const val COMMENT_LIST_PATH = "list"
    }

    @RequestMapping(value = [COMMENT_LIST_PATH], produces = ["application/json"], method = [(RequestMethod.GET)])
    fun getCommentList(
            @RequestParam("objectId") id: Long,
            @RequestParam("objectType") objectTypeOrdinal: Int,
            @RequestParam("pageNumber") pageNumber: Int,
            @RequestParam("pageSize") pageSize: Int
    ): CommentListResponseDto {
        logger.info("Request comment list for objectType {} with id {} pageNumber {} pageSize {}", objectTypeOrdinal, id, pageNumber, pageSize)

        val objectType = CocktailObjectType.values()[objectTypeOrdinal]

        val commentList = commentService.getCommentListForObject(objectType, id, PagingInfo(pageNumber, pageSize))
        return CommentListResponseDto(commentList)
    }
}
