package com.cocktailsguru.app.cocktail.service

import com.cocktailsguru.app.cocktail.domain.Cocktail
import com.cocktailsguru.app.cocktail.domain.CocktailList
import com.cocktailsguru.app.cocktail.domain.CocktailObjectType
import com.cocktailsguru.app.cocktail.repository.CocktailRepository
import com.cocktailsguru.app.common.domain.PagingInfo
import com.cocktailsguru.app.user.service.UserFavoriteService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service

@Service
class CocktailServiceImpl @Autowired constructor(
        private val cocktailRepository: CocktailRepository,
        private val userFavoriteService: UserFavoriteService
) : CocktailService {

    override fun getCocktailList(listRequest: PagingInfo): CocktailList {
        val cocktailList = cocktailRepository.findAll(PageRequest(listRequest.pageNumber, listRequest.pageSize)).content
        cocktailList.forEach(this::updateNumOfFavorite)
        return CocktailList(cocktailList, listRequest)
    }

    override fun getCocktailDetail(id: Long): Cocktail? {
        val cocktail = cocktailRepository.findOne(id)
        cocktail?.let {
            updateNumOfFavorite(it)
        }
        return cocktail
    }


    private fun updateNumOfFavorite(cocktail: Cocktail) {
        cocktail.numOfFavorite = userFavoriteService.getFavoriteObjects(CocktailObjectType.COCKTAIL, cocktail.id).size
    }
}