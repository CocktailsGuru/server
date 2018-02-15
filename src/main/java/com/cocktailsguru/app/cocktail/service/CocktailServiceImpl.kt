package com.cocktailsguru.app.cocktail.service

import com.cocktailsguru.app.cocktail.domain.Cocktail
import com.cocktailsguru.app.cocktail.domain.CocktailList
import com.cocktailsguru.app.cocktail.domain.CocktailObjectType
import com.cocktailsguru.app.cocktail.repository.CocktailRepository
import com.cocktailsguru.app.comment.service.CommentService
import com.cocktailsguru.app.common.domain.PagingInfo
import com.cocktailsguru.app.user.service.UserFavoriteService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service

@Service
class CocktailServiceImpl @Autowired constructor(
        private val cocktailRepository: CocktailRepository,
        private val userFavoriteService: UserFavoriteService,
        private val commentService: CommentService
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
            updateCommentList(it)
        }
        return cocktail
    }


    private fun updateNumOfFavorite(cocktail: Cocktail) {
        cocktail.numOfFavorite = userFavoriteService.getFavoriteObjects(CocktailObjectType.COCKTAIL, cocktail.id).size
    }

    private fun updateCommentList(cocktail: Cocktail) {
        cocktail.commentList = commentService.getCommentListForObject(CocktailObjectType.COCKTAIL, cocktail.id)
    }
}