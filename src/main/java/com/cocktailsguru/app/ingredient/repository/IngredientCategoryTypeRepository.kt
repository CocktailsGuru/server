package com.cocktailsguru.app.ingredient.repository

import com.cocktailsguru.app.ingredient.domain.IngredientCategoryType
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface IngredientCategoryTypeRepository : CrudRepository<IngredientCategoryType, Int>