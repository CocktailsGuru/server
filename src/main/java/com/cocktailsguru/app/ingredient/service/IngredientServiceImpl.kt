package com.cocktailsguru.app.ingredient.service

import com.cocktailsguru.app.common.domain.PagingInfo
import com.cocktailsguru.app.ingredient.domain.AlcoIngredient
import com.cocktailsguru.app.ingredient.domain.IngredientList
import com.cocktailsguru.app.ingredient.domain.NonAlcoIngredient
import com.cocktailsguru.app.ingredient.repository.AlcoIngredientRepository
import com.cocktailsguru.app.ingredient.repository.NonAlcoIngredientRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service

@Service
class IngredientServiceImpl @Autowired constructor(
        private val alcoIngredientRepository: AlcoIngredientRepository,
        private val nonAlcoIngredientRepository: NonAlcoIngredientRepository
) : IngredientService {

    override fun getAlcoIngredientList(listRequest: PagingInfo): IngredientList<AlcoIngredient> {
        val alcoIngredientList = alcoIngredientRepository.findAll(PageRequest(listRequest.pageNumber, listRequest.pageSize)).content

        return IngredientList(alcoIngredientList, listRequest)
    }

    override fun getNonAlcoIngredientList(listRequest: PagingInfo): IngredientList<NonAlcoIngredient> {
        val nonAlcoIngredientList = nonAlcoIngredientRepository.findAll(PageRequest(listRequest.pageNumber, listRequest.pageSize)).content
        return IngredientList(nonAlcoIngredientList, listRequest)
    }
}