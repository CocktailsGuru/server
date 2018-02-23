package com.cocktailsguru.app.cocktail.controller

import com.cocktailsguru.app.cocktail.domain.Cocktail
import com.cocktailsguru.app.cocktail.dto.CocktailDetailDto
import com.cocktailsguru.app.cocktail.dto.list.CocktailListResponseDto
import com.cocktailsguru.app.cocktail.service.CocktailService
import com.cocktailsguru.app.common.domain.PagingInfo
import com.cocktailsguru.app.common.dto.PagingDto
import com.cocktailsguru.app.ingredient.domain.IngredientCocktail
import com.cocktailsguru.app.utils.loggerFor
import org.modelmapper.Converter
import org.modelmapper.ModelMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
class CocktailController @Autowired constructor(private val cocktailService: CocktailService, private val modelMapper: ModelMapper) {

    val logger = loggerFor(javaClass)

    companion object {
        const val COCKTAIL_DETAIL_PATH = "/cocktailDetail"
        const val COCKTAIL_LIST_PATH = "/cocktailList"
    }

    @RequestMapping(value = [COCKTAIL_DETAIL_PATH], produces = ["application/json"], method = [(RequestMethod.GET)])
    fun getCocktailDetail(@RequestParam("id") id: Long): CocktailDetailDto? {
        logger.info("Request cocktail detail id {}", id)
        val cocktailDetail = cocktailService.getCocktailDetail(id)
        return if (cocktailDetail != null) {
            modelMapper.addConverter(Converter<IngredientCocktail, Long> { mappingContext ->
                mappingContext.source?.ingredient?.id
            })

            modelMapper.addConverter(Converter<Cocktail, Long> { mappingContext ->
                mappingContext.source?.id
            })

            modelMapper.map(cocktailDetail, CocktailDetailDto::class.java)
        } else {
            null
        }
    }

    @RequestMapping(value = [COCKTAIL_LIST_PATH], produces = ["application/json"], method = [RequestMethod.POST])
    fun getCocktailList(@RequestBody cocktailListRequestDto: PagingDto): CocktailListResponseDto {
        logger.info("Request cocktail list - {}", cocktailListRequestDto.toString())
        val pagingRequest = PagingInfo(cocktailListRequestDto.pageNumber, cocktailListRequestDto.pageSize)
        val cocktailList = cocktailService.getCocktailList(pagingRequest)
        return modelMapper.map(cocktailList, CocktailListResponseDto::class.java)
    }
}
