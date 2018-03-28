package com.cocktailsguru.app.ingredient.service

import com.cocktailsguru.app.cocktail.domain.CocktailObjectType
import com.cocktailsguru.app.comment.domain.CommentList
import com.cocktailsguru.app.comment.domain.add.NewCommentRequest
import com.cocktailsguru.app.comment.domain.add.NewCommentResult
import com.cocktailsguru.app.comment.service.CommentService
import com.cocktailsguru.app.common.domain.ObjectDetailRequest
import com.cocktailsguru.app.common.domain.PagingInfo
import com.cocktailsguru.app.ingredient.domain.Ingredient
import com.cocktailsguru.app.ingredient.domain.IngredientDetail
import com.cocktailsguru.app.ingredient.domain.IngredientList
import com.cocktailsguru.app.ingredient.domain.IngredientType
import com.cocktailsguru.app.ingredient.repository.IngredientRepository
import com.cocktailsguru.app.picture.domain.PictureList
import com.cocktailsguru.app.picture.service.PictureService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import javax.transaction.Transactional

@Service
open class IngredientServiceImpl @Autowired constructor(
        private val ingredientRepository: IngredientRepository,
        private val commentService: CommentService,
        private val pictureService: PictureService
) : IngredientService {

    override fun getPicturesList(id: Long, pictureRequest: PagingInfo): PictureList {
        return pictureService.getPictureListForObject(CocktailObjectType.INGREDIENT, id, pictureRequest)
    }

    override fun addNewComment(ingredientId: Long, commentRequest: NewCommentRequest): NewCommentResult {
        val ingredient = findIngredient(ingredientId)
        return commentService.addNewComment(ingredient, commentRequest)
    }


    override fun getCommentList(id: Long, commentRequest: PagingInfo): CommentList {
        return commentService.getCommentListForObject(CocktailObjectType.INGREDIENT, id, commentRequest)
    }

    override fun findIngredient(id: Long): Ingredient? {
        return ingredientRepository.findOne(id)
    }


    override fun getIngredientList(ingredientType: IngredientType?, listRequest: PagingInfo): IngredientList {
        if (listRequest.pageSize == 0) {
            return IngredientList(listOf(), listRequest)
        }

        val ingredientList = if (ingredientType != null) {
            ingredientRepository.findAllByIngredientType(ingredientType, listRequest.toPageRequest()).content
        } else {
            ingredientRepository.findAll(listRequest.toPageRequest()).content
        }
        return IngredientList(ingredientList, listRequest)
    }


    @Transactional
    override fun findIngredientDetail(detailRequest: ObjectDetailRequest): IngredientDetail? {
        val ingredient = findIngredient(detailRequest.id) ?: return null

        ingredient.numShowed++

        return IngredientDetail(
                ingredient,
                commentService.getCommentList(ingredient, PagingInfo(0, detailRequest.commentsSize))
        )
    }
}