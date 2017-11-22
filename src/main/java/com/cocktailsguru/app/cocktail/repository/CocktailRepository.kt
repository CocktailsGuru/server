package com.cocktailsguru.app.cocktail.repository

import com.cocktailsguru.app.cocktail.domain.Cocktail
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface CocktailRepository : CrudRepository<Cocktail, Long>