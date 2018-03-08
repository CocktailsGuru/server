package com.cocktailsguru.app.ingredient.repository

import com.cocktailsguru.app.ingredient.domain.Ingredient
import com.cocktailsguru.app.ingredient.domain.IngredientType
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.stereotype.Repository

@Repository
interface IngredientRepository : PagingAndSortingRepository<Ingredient, Long> {
    fun findAllByIngredientType(ingredientType: IngredientType, pageable: Pageable): Page<Ingredient>
}