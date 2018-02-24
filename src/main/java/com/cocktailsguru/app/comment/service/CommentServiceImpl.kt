package com.cocktailsguru.app.comment.service

import com.cocktailsguru.app.cocktail.domain.CocktailObjectType
import com.cocktailsguru.app.comment.repository.CommentRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class CommentServiceImpl @Autowired constructor(
        private val commentRepository: CommentRepository
) : CommentService {

    override fun getCommentListForObject(objectType: CocktailObjectType, id: Long) =
            commentRepository.findByObjectTypeAndObjectForeignKey(objectType, id)

}
