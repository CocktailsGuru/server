package com.cocktailsguru.app.comment.service

import com.cocktailsguru.app.cocktail.domain.Cocktail
import com.cocktailsguru.app.cocktail.domain.CocktailObjectType
import com.cocktailsguru.app.comment.domain.CommentList
import com.cocktailsguru.app.comment.repository.CommentRepository
import com.cocktailsguru.app.common.domain.PagingInfo
import com.cocktailsguru.app.ingredient.domain.Ingredient
import com.cocktailsguru.app.picture.domain.Picture
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class CommentServiceImpl @Autowired constructor(
        private val commentRepository: CommentRepository
//        private val userService: UserService,
//        private val cocktailObjectService: CocktailObjectService
) : CommentService {

//    override fun addNewComment(request: NewCommentRequest): NewCommentResult {
//        val authorUser = userService.findUserById(request.authorUserId)
//                ?: return NewCommentResult(NewCommentResultType.USER_NOT_FOUND, null)
//
//        val objectName = cocktailObjectService.findObjectName(request.objectForeignKey, request.objectType)
//                ?: return NewCommentResult(NewCommentResultType.OBJECT_NOT_FOUND, null)
//
//        val newComment = Comment(
//                0,
//                request.objectType,
//                request.objectForeignKey,
//                objectName,
//                authorUser,
//                request.content,
//                0,
//                0,
//                true,
//                LocalDateTime.now(),
//                LocalDateTime.now()
//        )
//
//        return NewCommentResult(NewCommentResultType.OK, commentRepository.save(newComment))
//    }

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
