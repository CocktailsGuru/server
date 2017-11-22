package com.cocktailsguru.app.cocktail.repository.ingredient

import com.cocktailsguru.app.cocktail.domain.ingredient.NonAlcoIngredient
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface NonAlcoIngredientRepository : CrudRepository<NonAlcoIngredient, Long>