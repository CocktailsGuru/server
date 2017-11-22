package com.cocktailsguru.app.cocktail.repository

import com.cocktailsguru.app.cocktail.domain.CocktailGlass
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface CocktailGlassRepository : CrudRepository<CocktailGlass, Long>