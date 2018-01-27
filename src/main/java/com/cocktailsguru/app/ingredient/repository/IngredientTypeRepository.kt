package com.cocktailsguru.app.ingredient.repository

import com.cocktailsguru.app.ingredient.domain.IngredientType
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface IngredientTypeRepository : CrudRepository<IngredientType, Int>