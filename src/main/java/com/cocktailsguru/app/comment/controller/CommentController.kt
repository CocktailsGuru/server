package com.cocktailsguru.app.comment.controller

import com.cocktailsguru.app.cocktail.domain.CocktailObjectType
import com.cocktailsguru.app.comment.dto.CommentResponseDto
import com.cocktailsguru.app.comment.service.CommentService
import com.cocktailsguru.app.utils.loggerFor
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import kotlin.streams.toList

@RestController
class CommentController @Autowired constructor(
        private val commentService: CommentService
) {

    private val logger = loggerFor(javaClass)

    companion object {
        const val COMMENT_LIST_PATH = "/commentList"
    }

    @RequestMapping(value = [COMMENT_LIST_PATH], produces = ["application/json"], method = [(RequestMethod.GET)])
    fun getCommentList(@RequestParam("objectId") id: Long, @RequestParam("objectType") objectTypeOrdinal: Int): List<CommentResponseDto> {
        logger.info("Request comment list for objectType {} with id {}", objectTypeOrdinal, id)

        val objectType = CocktailObjectType.values()[objectTypeOrdinal]

        val commentList = commentService.getCommentListForObject(objectType, id)

        return commentList.stream().map { CommentResponseDto(it) }.toList()
    }
}
