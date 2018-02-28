package com.cocktailsguru.app.comment.repository

import com.cocktailsguru.app.cocktail.domain.CocktailObjectType
import com.cocktailsguru.app.comment.domain.Comment
import org.springframework.data.domain.Pageable
import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.stereotype.Repository

@Repository
interface CommentRepository : PagingAndSortingRepository<Comment, Long> {
    fun findByObjectTypeAndObjectForeignKey(objectType: CocktailObjectType, objectForeignKey: Long, pageable: Pageable): List<Comment>
}
