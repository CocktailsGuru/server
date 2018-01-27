package com.cocktailsguru.app.ingredient.controller

import com.cocktailsguru.app.common.domain.PagingInfo
import com.cocktailsguru.app.common.dto.PagingDto
import com.cocktailsguru.app.ingredient.dto.list.AlcoIngredientListItemDto
import com.cocktailsguru.app.ingredient.dto.list.AlcoIngredientListResponseDto
import com.cocktailsguru.app.ingredient.dto.list.NonAlcoIngredientListItemDto
import com.cocktailsguru.app.ingredient.dto.list.NonAlcoIngredientListResponseDto
import com.cocktailsguru.app.ingredient.service.IngredientService
import com.cocktailsguru.app.utils.loggerFor
import org.modelmapper.ModelMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController

@RestController
class IngredientController @Autowired constructor(
        private val ingredientService: IngredientService,
        private val modelMapper: ModelMapper
) {
    val logger = loggerFor(javaClass)

    companion object {
        const val NON_ALCO_INGREDIENT_LIST_PATH = "/nonAlcoIngredientList"
        const val ALCO_INGREDIENT_LIST_PATH = "/alcoIngredientList"
    }

    @RequestMapping(value = [(NON_ALCO_INGREDIENT_LIST_PATH)], produces = ["application/json"], method = [RequestMethod.POST])
    fun getNonAlcoIngredientList(@RequestBody nonAlcoIngredientListRequestDto: PagingDto): NonAlcoIngredientListResponseDto {
        logger.info("Request non alco ingredient list - {}", nonAlcoIngredientListRequestDto.toString())
        val pagingRequest = PagingInfo(nonAlcoIngredientListRequestDto)

        val nonAlcoIngredientList = ingredientService.getNonAlcoIngredientList(pagingRequest)

        val nonAlcoIngredientListItemDtoList = nonAlcoIngredientList.list.map { it ->
            NonAlcoIngredientListItemDto(
                    it.id,
                    it.imageName,
                    it.nameGrouped
            )
        }

        val pagingDto = modelMapper.map(nonAlcoIngredientList.pagingInfo, PagingDto::class.java);
        return NonAlcoIngredientListResponseDto(nonAlcoIngredientListItemDtoList, pagingDto)
    }


    @RequestMapping(value = [(ALCO_INGREDIENT_LIST_PATH)], produces = ["application/json"], method = [RequestMethod.POST])
    fun getAlcoIngredientList(@RequestBody alcoIngredientListRequestDto: PagingDto): AlcoIngredientListResponseDto {
        logger.info("Request alco ingredient list - {}", alcoIngredientListRequestDto.toString())
        val pagingRequest = PagingInfo(alcoIngredientListRequestDto)

        val alcoIngredientList = ingredientService.getAlcoIngredientList(pagingRequest)

        val alcoIngredientListItemDtoList = alcoIngredientList.list.map { it ->
            AlcoIngredientListItemDto(
                    it.id,
                    it.imageName,
                    it.nameGrouped,
                    it.voltage
            )
        }

        val pagingDto = modelMapper.map(alcoIngredientList.pagingInfo, PagingDto::class.java);
        return AlcoIngredientListResponseDto(alcoIngredientListItemDtoList, pagingDto)
    }
}