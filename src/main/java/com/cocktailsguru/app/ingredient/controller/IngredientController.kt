package com.cocktailsguru.app.ingredient.controller

import com.cocktailsguru.app.common.domain.PagingInfo
import com.cocktailsguru.app.common.dto.ListResponseDto
import com.cocktailsguru.app.common.dto.PagingDto
import com.cocktailsguru.app.ingredient.controller.IngredientController.Companion.INGREDIENT_BASE_PATH
import com.cocktailsguru.app.ingredient.domain.Ingredient
import com.cocktailsguru.app.ingredient.dto.list.IngredientListItemDto
import com.cocktailsguru.app.ingredient.service.IngredientService
import com.cocktailsguru.app.utils.loggerFor
import org.modelmapper.ModelMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.access.annotation.Secured
import org.springframework.web.bind.annotation.*

@RestController
@Secured(value = ["ROLE_MOBILE"])
@RequestMapping(INGREDIENT_BASE_PATH)
open class IngredientController @Autowired constructor(
        private val ingredientService: IngredientService,
        private val modelMapper: ModelMapper
) {
    private val logger = loggerFor(javaClass)

    companion object {
        const val INGREDIENT_BASE_PATH = "ingredient"
        const val INGREDIENT_LIST_PATH = "list"
        const val INGREDIENT_DETAIL_PATH = "detail"
    }

    @RequestMapping(value = [(INGREDIENT_LIST_PATH)], produces = ["application/json"], method = [RequestMethod.POST])
    open fun getIngredientList(@RequestBody ingredientListRequestDto: PagingDto): ListResponseDto<IngredientListItemDto> {
        logger.info("Request ingredient list - {}", ingredientListRequestDto.toString())
        val pagingRequest = PagingInfo(ingredientListRequestDto)

        val ingredientList = ingredientService.getIngredientList(pagingRequest)

        val ingredientListItemDtoList = ingredientList.list.map { it ->
            IngredientListItemDto(
                    it.id,
                    it.imageName,
                    it.nameGrouped,
                    it.voltage
            )
        }

        val pagingDto = modelMapper.map(ingredientList.pagingInfo, PagingDto::class.java);
        return ListResponseDto(ingredientListItemDtoList, pagingDto)
    }

    //TODO create ingredient detail response DTO after mobile team finds out which response structure suits them the best - ingredientCategory
    @RequestMapping(value = [(INGREDIENT_DETAIL_PATH)], produces = ["application/json"], method = [(RequestMethod.GET)])
    open fun findIngredientDetail(@RequestParam("id") id: Long): Ingredient? {
        return ingredientService.findIngredient(id)
    }
}