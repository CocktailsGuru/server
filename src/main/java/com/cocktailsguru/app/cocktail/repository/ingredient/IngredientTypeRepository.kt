package com.cocktailsguru.app.cocktail.repository.ingredient

import com.cocktailsguru.app.cocktail.domain.ingredient.IngredientType
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface IngredientTypeRepository : CrudRepository<IngredientType, Int>