package com.cocktailsguru.app.picture.controller

import com.cocktailsguru.app.cocktail.domain.CocktailObjectType
import com.cocktailsguru.app.common.domain.PagingInfo
import com.cocktailsguru.app.picture.controller.PictureController.Companion.PICTURE_BASE_PATH
import com.cocktailsguru.app.picture.dto.PictureListResponseDto
import com.cocktailsguru.app.picture.service.PictureService
import com.cocktailsguru.app.utils.loggerFor
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.access.annotation.Secured
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

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
}
