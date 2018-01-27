package com.cocktailsguru.app.ingredient.repository

import com.cocktailsguru.app.ingredient.domain.AlcoIngredient
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface AlcoIngredientRepository : CrudRepository<AlcoIngredient, Long>