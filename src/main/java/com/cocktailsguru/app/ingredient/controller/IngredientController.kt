package com.cocktailsguru.app.ingredient.controller

import com.cocktailsguru.app.common.domain.PagingInfo
import com.cocktailsguru.app.common.dto.ListResponseDto
import com.cocktailsguru.app.common.dto.PagingDto
import com.cocktailsguru.app.ingredient.domain.AlcoIngredient
import com.cocktailsguru.app.ingredient.domain.NonAlcoIngredient
import com.cocktailsguru.app.ingredient.dto.list.AlcoIngredientListItemDto
import com.cocktailsguru.app.ingredient.dto.list.NonAlcoIngredientListItemDto
import com.cocktailsguru.app.ingredient.service.IngredientService
import com.cocktailsguru.app.utils.loggerFor
import org.modelmapper.ModelMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
class IngredientController @Autowired constructor(
        private val ingredientService: IngredientService,
        private val modelMapper: ModelMapper
) {
    val logger = loggerFor(javaClass)

    companion object {
        const val NON_ALCO_INGREDIENT_LIST_PATH = "/nonAlcoIngredientList"
        const val ALCO_INGREDIENT_LIST_PATH = "/alcoIngredientList"
        const val ALCO_INGREDIENT_DETAIL_PATH = "/alcoIngredientDetail"
        const val NON_ALCO_INGREDIENT_DETAIL_PATH = "/nonAlcoIngredientDetail"
    }

    @RequestMapping(value = [(NON_ALCO_INGREDIENT_LIST_PATH)], produces = ["application/json"], method = [RequestMethod.POST])
    fun getNonAlcoIngredientList(@RequestBody nonAlcoIngredientListRequestDto: PagingDto): ListResponseDto<NonAlcoIngredientListItemDto> {
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
        return ListResponseDto(nonAlcoIngredientListItemDtoList, pagingDto)
    }


    @RequestMapping(value = [(ALCO_INGREDIENT_LIST_PATH)], produces = ["application/json"], method = [RequestMethod.POST])
    fun getAlcoIngredientList(@RequestBody alcoIngredientListRequestDto: PagingDto): ListResponseDto<AlcoIngredientListItemDto> {
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
        return ListResponseDto(alcoIngredientListItemDtoList, pagingDto)
    }


    //TODO create alco ingredient detail response DTO after mobile team finds out which response structure suits them the best - ingredientCategory
    @RequestMapping(value = [(ALCO_INGREDIENT_DETAIL_PATH)], produces = ["application/json"], method = [(RequestMethod.GET)])
    fun findAlcoIngredientDetail(@RequestParam("id") id: Long): AlcoIngredient? {
        return ingredientService.findAlcoIngredient(id)
    }

    //TODO create alco ingredient detail response DTO after mobile team finds out which response structure suits them the best - ingredientCategory
    @RequestMapping(value = [NON_ALCO_INGREDIENT_DETAIL_PATH], produces = ["application/json"], method = [(RequestMethod.GET)])
    fun findNonAlcoIngredientDetail(@RequestParam("id") id: Long): NonAlcoIngredient? {
        return ingredientService.findNonAlcoIngredient(id)
    }
}