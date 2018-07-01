package com.cocktailsguru.app.comment.service

import com.cocktailsguru.app.cocktail.domain.Cocktail
import com.cocktailsguru.app.cocktail.domain.CocktailObjectType
import com.cocktailsguru.app.comment.domain.Comment
import com.cocktailsguru.app.comment.domain.CommentList
import com.cocktailsguru.app.comment.domain.add.NewCommentRequest
import com.cocktailsguru.app.comment.domain.add.NewCommentResult
import com.cocktailsguru.app.comment.domain.add.NewCommentResultType
import com.cocktailsguru.app.comment.repository.CommentRepository
import com.cocktailsguru.app.common.domain.PagingInfo
import com.cocktailsguru.app.ingredient.domain.Ingredient
import com.cocktailsguru.app.picture.domain.Picture
import com.cocktailsguru.app.user.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class CommentServiceImpl @Autowired constructor(
        private val commentRepository: CommentRepository
) : CommentService {

    override fun addNewComment(picture: Picture?, commentRequest: NewCommentRequest): NewCommentResult {
        picture ?: return NewCommentResult(NewCommentResultType.OBJECT_NOT_FOUND, null)

        val newComment = Comment(
                0,
                CocktailObjectType.USER_PICTURE,
                picture.id,
                picture.objectName,
                commentRequest.authorUser,
                commentRequest.content,
                0,
                0,
                true,
                LocalDateTime.now(),
                LocalDateTime.now()
        )

        return NewCommentResult(NewCommentResultType.OK, commentRepository.save(newComment))
    }

    override fun addNewComment(ingredient: Ingredient?, commentRequest: NewCommentRequest): NewCommentResult {
        ingredient ?: return NewCommentResult(NewCommentResultType.OBJECT_NOT_FOUND, null)

        val newComment = Comment(
                0,
                CocktailObjectType.INGREDIENT,
                ingredient.id,
                ingredient.name,
                commentRequest.authorUser,
                commentRequest.content,
                0,
                0,
                true,
                LocalDateTime.now(),
                LocalDateTime.now()
        )

        return NewCommentResult(NewCommentResultType.OK, commentRepository.save(newComment))
    }

    override fun addNewComment(cocktail: Cocktail?, commentRequest: NewCommentRequest): NewCommentResult {
        cocktail ?: return NewCommentResult(NewCommentResultType.OBJECT_NOT_FOUND, null)

        val newComment = Comment(
                0,
                CocktailObjectType.COCKTAIL,
                cocktail.id,
                cocktail.name,
                commentRequest.authorUser,
                commentRequest.content,
                0,
                0,
                true,
                LocalDateTime.now(),
                LocalDateTime.now()
        )

        return NewCommentResult(NewCommentResultType.OK, commentRepository.save(newComment))
    }

    override fun getCommentList(cocktail: Cocktail, pagingInfo: PagingInfo) =
            getCommentListForObject(CocktailObjectType.COCKTAIL, cocktail.id, pagingInfo)

    override fun getCommentList(ingredient: Ingredient, pagingInfo: PagingInfo) =
            getCommentListForObject(CocktailObjectType.INGREDIENT, ingredient.id, pagingInfo)

    override fun getCommentList(picture: Picture, pagingInfo: PagingInfo) =
            getCommentListForObject(CocktailObjectType.USER_PICTURE, picture.id, pagingInfo)

    override fun getCommentListForObject(objectType: CocktailObjectType, id: Long, pagingInfo: PagingInfo): CommentList {
        return if (pagingInfo.pageSize == 0) {
            CommentList(listOf(), pagingInfo)
        } else {
            val commentList = commentRepository.findByObjectTypeAndObjectForeignKey(objectType, id, pagingInfo.toPageRequest())
            CommentList(commentList, pagingInfo)
        }
    }

}
