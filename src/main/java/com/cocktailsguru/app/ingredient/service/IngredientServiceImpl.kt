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
import com.cocktailsguru.app.ingredient.repository.IngredientRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class IngredientServiceImpl @Autowired constructor(
        private val ingredientRepository: IngredientRepository,
        private val commentService: CommentService
) : IngredientService {

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


    override fun getIngredientList(listRequest: PagingInfo): IngredientList {
        return if (listRequest.pageSize == 0) {
            IngredientList(listOf(), listRequest)
        } else {
            val list = ingredientRepository.findAll(listRequest.toPageRequest()).content
            IngredientList(list, listRequest)
        }
    }


    override fun findIngredientDetail(detailRequest: ObjectDetailRequest): IngredientDetail? {
        val ingredient = findIngredient(detailRequest.id)
                ?: return null

        return IngredientDetail(
                ingredient,
                commentService.getCommentList(ingredient, PagingInfo(0, detailRequest.commentsSize))
        )
    }
}
