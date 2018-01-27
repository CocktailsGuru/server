package com.cocktailsguru.app.ingredient.service

import com.cocktailsguru.app.common.domain.PagingInfo
import com.cocktailsguru.app.ingredient.domain.AlcoIngredient
import com.cocktailsguru.app.ingredient.domain.IngredientList
import com.cocktailsguru.app.ingredient.domain.NonAlcoIngredient

interface IngredientService {
    fun getNonAlcoIngredientList(listRequest: PagingInfo): IngredientList<NonAlcoIngredient>

    fun getAlcoIngredientList(listRequest: PagingInfo): IngredientList<AlcoIngredient>

    fun findAlcoIngredient(id: Long): AlcoIngredient?

    fun findNonAlcoIngredient(id: Long): NonAlcoIngredient?
}