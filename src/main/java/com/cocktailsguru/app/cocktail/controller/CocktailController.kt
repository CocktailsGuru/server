package com.cocktailsguru.app.cocktail.controller

import com.cocktailsguru.app.cocktail.controller.CocktailController.Companion.COCKTAIL_BASE_PATH
import com.cocktailsguru.app.cocktail.domain.detail.CocktailDetailRequest
import com.cocktailsguru.app.cocktail.dto.detail.CocktailDetailDto
import com.cocktailsguru.app.cocktail.dto.detail.CocktailDetailResponseDto
import com.cocktailsguru.app.cocktail.dto.list.CocktailListResponseDto
import com.cocktailsguru.app.cocktail.service.CocktailService
import com.cocktailsguru.app.comment.dto.CommentListResponseDto
import com.cocktailsguru.app.common.domain.ObjectDetailRequest
import com.cocktailsguru.app.common.domain.PagingInfo
import com.cocktailsguru.app.picture.dto.PictureListResponseDto
import com.cocktailsguru.app.utils.loggerFor
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.access.annotation.Secured
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@Secured(value = ["ROLE_MOBILE"])
@RequestMapping(COCKTAIL_BASE_PATH)
open class CocktailController @Autowired constructor(
        private val cocktailService: CocktailService
) {

    private val logger = loggerFor(javaClass)

    companion object {
        const val COCKTAIL_BASE_PATH = "cocktail"
        const val COCKTAIL_DETAIL_PATH = "detail"
        const val COCKTAIL_LIST_PATH = "list"
    }

    @RequestMapping(value = [COCKTAIL_DETAIL_PATH], produces = ["application/json"], method = [(RequestMethod.GET)])
    open fun getCocktailDetail(
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
    open fun getCocktailList(
            @RequestParam("pageNumber") pageNumber: Int,
            @RequestParam("pageSize") pageSize: Int
    ): CocktailListResponseDto {
        logger.info("Requested cocktail list pageNumber {} page size {}", pageNumber, pageSize)
        val pagingRequest = PagingInfo(pageNumber, pageSize)
        val cocktailList = cocktailService.getCocktailList(pagingRequest)
        return CocktailListResponseDto(cocktailList)
    }
}
