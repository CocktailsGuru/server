package com.cocktailsguru.app.picture.controller

import com.cocktailsguru.app.cocktail.domain.CocktailObjectType
import com.cocktailsguru.app.picture.dto.PictureResponseDto
import com.cocktailsguru.app.picture.service.PictureService
import com.cocktailsguru.app.utils.loggerFor
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import kotlin.streams.toList

@RestController
class PictureController @Autowired constructor(
        private val pictureService: PictureService
) {

    private val logger = loggerFor(javaClass)

    companion object {
        const val PICTURE_LIST_PATH = "/pictureList"
    }

    @RequestMapping(value = [PICTURE_LIST_PATH], produces = ["application/json"], method = [(RequestMethod.GET)])
    fun getPictureList(@RequestParam("objectId") id: Long, @RequestParam("objectType") objectTypeOrdinal: Int): List<PictureResponseDto> {
        logger.info("Requested picture list for objectType {} with id {}", objectTypeOrdinal, id)

        val objectType = CocktailObjectType.values()[objectTypeOrdinal]

        val pictureList = pictureService.getPictureListForObject(objectType, id)

        return pictureList.stream().map { PictureResponseDto(it) }.toList()
    }
}
