package com.cocktailsguru.app.picture.service

import com.cocktailsguru.app.cocktail.domain.Cocktail
import com.cocktailsguru.app.cocktail.domain.CocktailObjectType
import com.cocktailsguru.app.comment.domain.add.NewCommentRequest
import com.cocktailsguru.app.comment.domain.add.NewCommentResult
import com.cocktailsguru.app.comment.service.CommentService
import com.cocktailsguru.app.common.domain.PagingInfo
import com.cocktailsguru.app.picture.domain.Picture
import com.cocktailsguru.app.picture.domain.PictureList
import com.cocktailsguru.app.picture.repository.PictureRepository
import org.springframework.stereotype.Service

@Service
class PictureServiceImpl(
        private val pictureRepository: PictureRepository,
        private val commentService: CommentService
) : PictureService {


    override fun addNewComment(pictureId: Long, commentRequest: NewCommentRequest): NewCommentResult {
        val picture = findPicture(pictureId)
        return commentService.addNewComment(picture, commentRequest)
    }

    override fun getPictureList(cocktail: Cocktail, pagingInfo: PagingInfo) =
            getPictureListForObject(CocktailObjectType.COCKTAIL, cocktail.id, pagingInfo)

    override fun getPictureListForObject(objectType: CocktailObjectType, id: Long, pagingInfo: PagingInfo): PictureList {
        return if (pagingInfo.pageSize == 0) {
            PictureList(listOf(), pagingInfo)
        } else {
            val pictureList = pictureRepository.findByObjectTypeAndObjectForeignKey(objectType, id, pagingInfo.toPageRequest())
            PictureList(pictureList, pagingInfo)
        }
    }

    override fun findPicture(id: Long): Picture? {
        return pictureRepository.findById(id).orElse(null)
    }
}