package com.cocktailsguru.app.cocktail.service

import com.cocktailsguru.app.cocktail.domain.Cocktail
import com.cocktailsguru.app.cocktail.domain.CocktailList
import com.cocktailsguru.app.cocktail.repository.CocktailRepository
import com.cocktailsguru.app.common.domain.PagingInfo
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service

@Service
class CocktailServiceImpl @Autowired constructor(private val cocktailRepository: CocktailRepository) : CocktailService {

    override fun getCocktailList(listRequest: PagingInfo): CocktailList {
        val cocktailList = cocktailRepository.findAll(PageRequest(listRequest.pageNumber, listRequest.pageSize)).content
        return CocktailList(cocktailList, listRequest)
    }

    override fun getCocktailDetail(id: Long): Cocktail? = cocktailRepository.findOne(id)
}