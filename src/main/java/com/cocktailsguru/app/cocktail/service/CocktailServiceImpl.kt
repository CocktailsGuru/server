package com.cocktailsguru.app.cocktail.service

import com.cocktailsguru.app.cocktail.domain.Cocktail
import com.cocktailsguru.app.cocktail.domain.CocktailList
import com.cocktailsguru.app.cocktail.domain.CocktailObjectType
import com.cocktailsguru.app.cocktail.domain.detail.CocktailDetail
import com.cocktailsguru.app.cocktail.domain.detail.CocktailDetailRequest
import com.cocktailsguru.app.cocktail.repository.CocktailRepository
import com.cocktailsguru.app.comment.domain.CommentList
import com.cocktailsguru.app.comment.domain.add.NewCommentRequest
import com.cocktailsguru.app.comment.domain.add.NewCommentResult
import com.cocktailsguru.app.comment.service.CommentService
import com.cocktailsguru.app.common.domain.PagingInfo
import com.cocktailsguru.app.picture.domain.PictureList
import com.cocktailsguru.app.picture.service.PictureService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import javax.transaction.Transactional

@Service
open class CocktailServiceImpl @Autowired constructor(
        private val cocktailRepository: CocktailRepository,
        private val commentService: CommentService,
        private val pictureService: PictureService
) : CocktailService {

    override fun getPicturesList(id: Long, pictureRequest: PagingInfo): PictureList {
        return pictureService.getPictureListForObject(CocktailObjectType.COCKTAIL, id, pictureRequest)
    }

    override fun addNewComment(cocktailId: Long, commentRequest: NewCommentRequest): NewCommentResult {
        val cocktail = findCocktail(cocktailId)
        return commentService.addNewComment(cocktail, commentRequest)
    }

    override fun getCommentList(id: Long, commentRequest: PagingInfo): CommentList {
        return commentService.getCommentListForObject(CocktailObjectType.COCKTAIL, id, commentRequest)
    }

    override fun findCocktail(id: Long): Cocktail? {
        val cocktail = cocktailRepository.findOne(id)
                ?: return null

        return cocktail
    }

    override fun getCocktailList(listRequest: PagingInfo): CocktailList {
        return if (listRequest.pageSize == 0) {
            CocktailList(listOf(), listRequest)
        } else {
            val cocktailList = cocktailRepository.findAll(listRequest.toPageRequest()).content
            CocktailList(cocktailList, listRequest)
        }
    }

    @Transactional
    override fun findCocktailDetail(request: CocktailDetailRequest): CocktailDetail? {
        val cocktail = findCocktail(request.detailRequest.id) ?: return null

        cocktail.numShowed++

        return CocktailDetail(
                cocktail,
                commentService.getCommentList(cocktail, PagingInfo(0, request.detailRequest.commentsSize)),
                pictureService.getPictureList(cocktail, PagingInfo(0, request.picturesSize))
        )
    }
}