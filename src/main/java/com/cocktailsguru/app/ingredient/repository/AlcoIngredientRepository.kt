package com.cocktailsguru.app.ingredient.repository

import com.cocktailsguru.app.ingredient.domain.AlcoIngredient
import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.stereotype.Repository

@Repository
interface AlcoIngredientRepository : PagingAndSortingRepository<AlcoIngredient, Long>