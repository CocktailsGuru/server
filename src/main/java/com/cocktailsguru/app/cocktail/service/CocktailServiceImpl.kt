package com.cocktailsguru.app.cocktail.service

import com.cocktailsguru.app.cocktail.domain.Cocktail
import com.cocktailsguru.app.cocktail.repository.CocktailRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class CocktailServiceImpl @Autowired constructor(private val cocktailRepository: CocktailRepository) : CocktailService {

    override fun getCocktailDetail(id: Long): Cocktail? = cocktailRepository.findOne(id)
}