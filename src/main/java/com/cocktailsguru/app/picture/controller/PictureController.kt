package com.cocktailsguru.app.picture.controller

import com.cocktailsguru.app.cocktail.domain.CocktailObjectType
import com.cocktailsguru.app.comment.domain.add.NewCommentResultType
import com.cocktailsguru.app.comment.dto.NewCommentRequestDto
import com.cocktailsguru.app.comment.dto.NewCommentResponseDto
import com.cocktailsguru.app.common.domain.PagingInfo
import com.cocktailsguru.app.common.dto.UnauthorizedException
import com.cocktailsguru.app.picture.controller.PictureController.Companion.PICTURE_BASE_PATH
import com.cocktailsguru.app.picture.dto.PictureListResponseDto
import com.cocktailsguru.app.picture.service.PictureService
import com.cocktailsguru.app.utils.loggerFor
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.access.annotation.Secured
import org.springframework.web.bind.annotation.*

@RestController
@Secured(value = ["ROLE_MOBILE"])
@RequestMapping(PICTURE_BASE_PATH)
open class PictureController @Autowired constructor(
        private val pictureService: PictureService
) {

    private val logger = loggerFor(javaClass)

    companion object {
        const val PICTURE_BASE_PATH = "picture"
        const val PICTURE_LIST_PATH = "list"
        const val ADD_COMMENT_PATH = "addComment"
    }

    @RequestMapping(value = [PICTURE_LIST_PATH], produces = ["application/json"], method = [(RequestMethod.GET)])
    open fun getPictureList(
            @RequestParam("objectId") id: Long,
            @RequestParam("objectType") objectTypeOrdinal: Int,
            @RequestParam("pageNumber") pageNumber: Int,
            @RequestParam("pageSize") pageSize: Int
    ): PictureListResponseDto {
        logger.info("Requested picture list for objectType {} with id {} pageNumber {} pageSize {}", objectTypeOrdinal, id, pageNumber, pageSize)

        val objectType = CocktailObjectType.values()[objectTypeOrdinal]

        val pictureList = pictureService.getPictureListForObject(objectType, id, PagingInfo(pageNumber, pageSize))

        return PictureListResponseDto(pictureList)
    }

    @RequestMapping(value = [ADD_COMMENT_PATH], produces = ["application/json"], method = [RequestMethod.POST])
    @ResponseBody
    open fun addComment(@RequestBody commentRequestDto: NewCommentRequestDto): NewCommentResponseDto {
        val pictureId = commentRequestDto.objectId
        logger.info("Requested new comment for picture {} author", pictureId, commentRequestDto.userTokenDto.userId)
        val newCommentRequest = commentRequestDto.toNewCommentRequest()
        val result = pictureService.addNewComment(pictureId, newCommentRequest)
        return when (result.resultType) {
            NewCommentResultType.USER_NOT_FOUND -> throw UnauthorizedException()
            else -> NewCommentResponseDto(result)
        }
    }
}
