package com.cocktailsguru.app.cocktail.controller

import com.cocktailsguru.app.cocktail.dto.CocktailDetailDto
import com.cocktailsguru.app.cocktail.service.CocktailService
import org.modelmapper.ModelMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class CocktailController @Autowired constructor(private val cocktailService: CocktailService, private val modelMapper: ModelMapper) {

    companion object {
        const val COCKTAIL_DETAIL_PATH = "/cocktailDetail"
    }

    @RequestMapping(value = [COCKTAIL_DETAIL_PATH], produces = ["application/json"], method = [(RequestMethod.GET)])
    fun getCocktailDetail(@RequestParam("id") id: Long): CocktailDetailDto? {
        val cocktailDetail = cocktailService.getCocktailDetail(id)
        return if (cocktailDetail != null) {
            modelMapper.map(cocktailDetail, CocktailDetailDto::class.java)
        } else {
            null
        }
    }

}
