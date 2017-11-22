package com.cocktailsguru.app.cocktail.repository.ingredient

import com.cocktailsguru.app.cocktail.domain.ingredient.AlcoIngredient
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface AlcoIngredientRepository : CrudRepository<AlcoIngredient, Long>