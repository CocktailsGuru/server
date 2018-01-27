package com.cocktailsguru.app.ingredient.repository

import com.cocktailsguru.app.ingredient.domain.NonAlcoIngredient
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface NonAlcoIngredientRepository : CrudRepository<NonAlcoIngredient, Long>