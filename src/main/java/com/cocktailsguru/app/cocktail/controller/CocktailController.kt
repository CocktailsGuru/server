package com.cocktailsguru.app.cocktail.controller

import com.cocktailsguru.app.cocktail.controller.CocktailController.Companion.COCKTAIL_BASE_PATH
import com.cocktailsguru.app.cocktail.domain.detail.CocktailDetailRequest
import com.cocktailsguru.app.cocktail.dto.detail.CocktailDetailDto
import com.cocktailsguru.app.cocktail.dto.detail.CocktailDetailResponseDto
import com.cocktailsguru.app.cocktail.dto.list.CocktailListResponseDto
import com.cocktailsguru.app.cocktail.service.CocktailService
import com.cocktailsguru.app.comment.domain.add.NewCommentRequest
import com.cocktailsguru.app.comment.dto.CommentListResponseDto
import com.cocktailsguru.app.comment.dto.NewCommentRequestDto
import com.cocktailsguru.app.comment.dto.NewCommentResponseDto
import com.cocktailsguru.app.common.domain.ObjectDetailRequest
import com.cocktailsguru.app.common.domain.PagingInfo
import com.cocktailsguru.app.picture.dto.PictureListResponseDto
import com.cocktailsguru.app.utils.loggerFor
import com.cocktailsguru.app.verification.service.UserVerificationService
import org.springframework.security.access.annotation.Secured
import org.springframework.web.bind.annotation.*

@RestController
@Secured(value = ["ROLE_MOBILE"])
@RequestMapping(COCKTAIL_BASE_PATH)
class CocktailController(
        private val cocktailService: CocktailService,
        private val userVerificationService: UserVerificationService
) {

    private val logger = loggerFor(javaClass)

    companion object {
        const val COCKTAIL_BASE_PATH = "cocktail"
        const val COCKTAIL_DETAIL_PATH = "detail"
        const val COCKTAIL_LIST_PATH = "list"
        const val COMMENT_LIST_PATH = "comments"
        const val PICTURE_LIST_PATH = "pictures"
        const val ADD_COMMENT_PATH = "addComment"
    }

    @RequestMapping(value = [COCKTAIL_DETAIL_PATH], produces = ["application/json"], method = [(RequestMethod.GET)])
    fun getCocktailDetail(
            @RequestParam("id") id: Long,
            @RequestParam("commentsSize", required = false, defaultValue = "0") commentsSize: Int,
            @RequestParam("picturesSize", required = false, defaultValue = "0") picturesSize: Int
    ): CocktailDetailResponseDto? {
        logger.info("Request cocktail detail id {} picturesSize {} commentsSize {}", id, picturesSize, commentsSize)
        val detailRequest = CocktailDetailRequest(ObjectDetailRequest(id, commentsSize), picturesSize)
        val cocktailDetail = cocktailService.findCocktailDetail(detailRequest)

        return cocktailDetail?.let {
            CocktailDetailResponseDto(
                    CocktailDetailDto(cocktailDetail.detail),
                    CommentListResponseDto(cocktailDetail.commentList),
                    PictureListResponseDto(cocktailDetail.pictureList)
            )
        }
    }

    @RequestMapping(value = [COCKTAIL_LIST_PATH], produces = ["application/json"], method = [RequestMethod.GET])
    fun getCocktailList(
            @RequestParam("pageNumber") pageNumber: Int,
            @RequestParam("pageSize") pageSize: Int
    ): CocktailListResponseDto {
        logger.info("Requested cocktail list pageNumber {} page size {}", pageNumber, pageSize)
        val pagingRequest = PagingInfo(pageNumber, pageSize)
        val cocktailList = cocktailService.getCocktailList(pagingRequest)
        return CocktailListResponseDto(cocktailList)
    }


    @RequestMapping(value = [COMMENT_LIST_PATH], produces = ["application/json"], method = [RequestMethod.GET])
    fun getCommentList(
            @RequestParam("id") id: Long,
            @RequestParam("pageNumber") pageNumber: Int,
            @RequestParam("pageSize") pageSize: Int
    ): CommentListResponseDto {
        logger.info("Requested comment list for cocktail {} pageNumber {} page size {}", id, pageNumber, pageSize)
        val commentList = cocktailService.getCommentList(id, PagingInfo(pageNumber, pageSize))
        return CommentListResponseDto(commentList)
    }


    @RequestMapping(value = [PICTURE_LIST_PATH], produces = ["application/json"], method = [RequestMethod.GET])
    fun getPictureList(
            @RequestParam("id") id: Long,
            @RequestParam("pageNumber") pageNumber: Int,
            @RequestParam("pageSize") pageSize: Int
    ): PictureListResponseDto {
        logger.info("Requested picture list for cocktail {} pageNumber {} page size {}", id, pageNumber, pageSize)
        val pictureList = cocktailService.getPicturesList(id, PagingInfo(pageNumber, pageSize))
        return PictureListResponseDto(pictureList)
    }


    @RequestMapping(value = [ADD_COMMENT_PATH], produces = ["application/json"], method = [RequestMethod.POST])
    @ResponseBody
    fun addComment(@RequestBody commentRequestDto: NewCommentRequestDto): NewCommentResponseDto {
        val cocktailId = commentRequestDto.objectId
        logger.info("Requested new comment for cocktail {}", cocktailId)
        val authorUser = userVerificationService.getLoggedUser()
        val newCommentRequest = NewCommentRequest(commentRequestDto.content, authorUser)
        val result = cocktailService.addNewComment(cocktailId, newCommentRequest)
        return NewCommentResponseDto(result)
    }
}
