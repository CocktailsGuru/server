package com.cocktailsguru.app.ingredient.repository

import com.cocktailsguru.app.ingredient.domain.NonAlcoIngredient
import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.stereotype.Repository

@Repository
interface NonAlcoIngredientRepository : PagingAndSortingRepository<NonAlcoIngredient, Long>