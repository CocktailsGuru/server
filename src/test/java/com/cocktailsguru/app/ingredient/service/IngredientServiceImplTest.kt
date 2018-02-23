package com.cocktailsguru.app.ingredient.service

import com.cocktailsguru.app.common.domain.PagingInfo
import com.cocktailsguru.app.ingredient.domain.Ingredient
import com.cocktailsguru.app.ingredient.repository.IngredientRepository
import com.nhaarman.mockito_kotlin.*
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import kotlin.test.assertEquals
import kotlin.test.assertTrue

@RunWith(MockitoJUnitRunner::class)
class IngredientServiceImplTest {

    private val anyIngredientRepository = mock<IngredientRepository>()

    private val ingredientService = IngredientServiceImpl(anyIngredientRepository)


    @Test
    fun givenNoIngredientsInRepositoryWhenRequestingIngredientListShouldReturnEmptyList() {
        val anyRequest = mock<PagingInfo>()
        val anyPageResponse = mock<Page<Ingredient>>()
        val anyPageSize = 10
        val anyPageNumber = 20
        whenever(anyRequest.pageSize).thenReturn(anyPageSize)
        whenever(anyRequest.pageNumber).thenReturn(anyPageNumber)
        whenever(anyPageResponse.content).thenReturn(listOf())
        val pagingCaptor = argumentCaptor<PageRequest>()

        whenever(anyIngredientRepository.findAll(any<Pageable>())).thenReturn(anyPageResponse)

        val ingredientList = ingredientService.getIngredientList(anyRequest)

        verify(anyIngredientRepository).findAll(pagingCaptor.capture())

        assertEquals(pagingCaptor.firstValue.pageNumber, anyRequest.pageNumber)
        assertEquals(pagingCaptor.firstValue.pageSize, anyRequest.pageSize)

        assertEquals(anyRequest, ingredientList.pagingInfo)
        assertTrue(ingredientList.list.isEmpty())
    }


    @Test
    fun givenIngredientsInRepositoryWhenRequestinIngredientListShouldReturnNonEmptyList() {
        val anyRequest = mock<PagingInfo>()
        val anyIngredient = mock<Ingredient>()
        val anyPageResponse = mock<Page<Ingredient>>()
        val anyPageSize = 10
        val anyPageNumber = 20
        whenever(anyRequest.pageSize).thenReturn(anyPageSize)
        whenever(anyRequest.pageNumber).thenReturn(anyPageNumber)
        whenever(anyPageResponse.content).thenReturn(listOf(anyIngredient))
        val pagingCaptor = argumentCaptor<PageRequest>()

        whenever(anyIngredientRepository.findAll(any<Pageable>())).thenReturn(anyPageResponse)

        val ingredientList = ingredientService.getIngredientList(anyRequest)

        verify(anyIngredientRepository).findAll(pagingCaptor.capture())

        assertEquals(pagingCaptor.firstValue.pageNumber, anyRequest.pageNumber)
        assertEquals(pagingCaptor.firstValue.pageSize, anyRequest.pageSize)

        assertEquals(anyRequest, ingredientList.pagingInfo)
        assertEquals(1, ingredientList.list.size)
        assertEquals(anyIngredient, ingredientList.list[0])
    }
}