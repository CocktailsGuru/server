package com.cocktailsguru.app.ingredient.repository

import com.cocktailsguru.app.ingredient.domain.Ingredient
import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.stereotype.Repository

@Repository
interface IngredientRepository : PagingAndSortingRepository<Ingredient, Long>