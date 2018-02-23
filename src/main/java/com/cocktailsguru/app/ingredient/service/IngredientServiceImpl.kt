package com.cocktailsguru.app.ingredient.service

import com.cocktailsguru.app.common.domain.PagingInfo
import com.cocktailsguru.app.ingredient.domain.Ingredient
import com.cocktailsguru.app.ingredient.domain.IngredientList
import com.cocktailsguru.app.ingredient.repository.IngredientRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service

@Service
class IngredientServiceImpl @Autowired constructor(
        private val ingredientRepository: IngredientRepository
) : IngredientService {


    override fun getIngredientList(listRequest: PagingInfo): IngredientList {
        val ingredientList = ingredientRepository.findAll(PageRequest(listRequest.pageNumber, listRequest.pageSize)).content
        return IngredientList(ingredientList, listRequest)
    }

    override fun findIngredient(id: Long): Ingredient? {
        return ingredientRepository.findOne(id)
    }
}