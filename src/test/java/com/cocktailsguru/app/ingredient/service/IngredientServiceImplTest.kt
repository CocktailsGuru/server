package com.cocktailsguru.app.ingredient.service

import com.cocktailsguru.app.common.domain.PagingInfo
import com.cocktailsguru.app.ingredient.domain.AlcoIngredient
import com.cocktailsguru.app.ingredient.domain.NonAlcoIngredient
import com.cocktailsguru.app.ingredient.repository.AlcoIngredientRepository
import com.cocktailsguru.app.ingredient.repository.NonAlcoIngredientRepository
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

    private val anyNonAlcoIngredientRepository = mock<NonAlcoIngredientRepository>()

    private val anyAlcoIngredientRepository = mock<AlcoIngredientRepository>()

    private val ingredientService = IngredientServiceImpl(anyAlcoIngredientRepository, anyNonAlcoIngredientRepository)

    @Test
    fun givenIngredientsInRepositoryWhenRequestingAlcoIngredientListShouldReturnNonEmptyList() {
        val anyRequest = mock<PagingInfo>()
        val anyAlcoIngredient = mock<AlcoIngredient>()
        val anyPageResponse = mock<Page<AlcoIngredient>>()
        val anyPageSize = 10
        val anyPageNumber = 20
        whenever(anyRequest.pageSize).thenReturn(anyPageSize)
        whenever(anyRequest.pageNumber).thenReturn(anyPageNumber)
        whenever(anyPageResponse.content).thenReturn(listOf(anyAlcoIngredient))
        val pagingCaptor = argumentCaptor<PageRequest>()

        whenever(anyAlcoIngredientRepository.findAll(any<Pageable>())).thenReturn(anyPageResponse)

        val alcoIngredientList = ingredientService.getAlcoIngredientList(anyRequest)

        verify(anyAlcoIngredientRepository).findAll(pagingCaptor.capture())
        verifyZeroInteractions(anyNonAlcoIngredientRepository)

        assertEquals(pagingCaptor.firstValue.pageNumber, anyRequest.pageNumber)
        assertEquals(pagingCaptor.firstValue.pageSize, anyRequest.pageSize)

        assertEquals(anyRequest, alcoIngredientList.pagingInfo)
        assertEquals(1, alcoIngredientList.list.size)
        assertEquals(anyAlcoIngredient, alcoIngredientList.list[0])
    }

    @Test
    fun givenIngredientsInRepositoryWhenRequestingNonAlcoIngredientListShouldReturnNonEmptyList() {
        val anyRequest = mock<PagingInfo>()
        val anyNonAlcoIngredient = mock<NonAlcoIngredient>()
        val anyPageResponse = mock<Page<NonAlcoIngredient>>()
        val anyPageSize = 10
        val anyPageNumber = 20
        whenever(anyRequest.pageSize).thenReturn(anyPageSize)
        whenever(anyRequest.pageNumber).thenReturn(anyPageNumber)
        whenever(anyPageResponse.content).thenReturn(listOf(anyNonAlcoIngredient))
        val pagingCaptor = argumentCaptor<PageRequest>()

        whenever(anyNonAlcoIngredientRepository.findAll(any<Pageable>())).thenReturn(anyPageResponse)

        val nonAlcoIngredientList = ingredientService.getNonAlcoIngredientList(anyRequest)

        verify(anyNonAlcoIngredientRepository).findAll(pagingCaptor.capture())
        verifyZeroInteractions(anyAlcoIngredientRepository)

        assertEquals(pagingCaptor.firstValue.pageNumber, anyRequest.pageNumber)
        assertEquals(pagingCaptor.firstValue.pageSize, anyRequest.pageSize)

        assertEquals(anyRequest, nonAlcoIngredientList.pagingInfo)
        assertEquals(1, nonAlcoIngredientList.list.size)
        assertEquals(anyNonAlcoIngredient, nonAlcoIngredientList.list[0])
    }


    @Test
    fun givenNoDataInRepositoryWhenRequestingAlcoIngredientListShouldReturnEmptyList() {
        val anyRequest = mock<PagingInfo>()
        val anyPageResponse = mock<Page<AlcoIngredient>>()
        val anyPageSize = 10
        val anyPageNumber = 20
        whenever(anyRequest.pageSize).thenReturn(anyPageSize)
        whenever(anyRequest.pageNumber).thenReturn(anyPageNumber)
        whenever(anyPageResponse.content).thenReturn(listOf())
        val pagingCaptor = argumentCaptor<PageRequest>()

        whenever(anyAlcoIngredientRepository.findAll(any<Pageable>())).thenReturn(anyPageResponse)

        val alcoIngredientList = ingredientService.getAlcoIngredientList(anyRequest)

        verify(anyAlcoIngredientRepository).findAll(pagingCaptor.capture())
        verifyZeroInteractions(anyNonAlcoIngredientRepository)

        assertEquals(pagingCaptor.firstValue.pageNumber, anyRequest.pageNumber)
        assertEquals(pagingCaptor.firstValue.pageSize, anyRequest.pageSize)

        assertEquals(anyRequest, alcoIngredientList.pagingInfo)
        assertTrue(alcoIngredientList.list.isEmpty())
    }


    @Test
    fun givenNoDataInRepositoryWhenRequestingNonAlcoIngredientListShouldReturnEmptyList() {
        val anyRequest = mock<PagingInfo>()
        val anyPageResponse = mock<Page<NonAlcoIngredient>>()
        val anyPageSize = 10
        val anyPageNumber = 20
        whenever(anyRequest.pageSize).thenReturn(anyPageSize)
        whenever(anyRequest.pageNumber).thenReturn(anyPageNumber)
        whenever(anyPageResponse.content).thenReturn(listOf())
        val pagingCaptor = argumentCaptor<PageRequest>()

        whenever(anyNonAlcoIngredientRepository.findAll(any<Pageable>())).thenReturn(anyPageResponse)

        val nonAlcoIngredientList = ingredientService.getNonAlcoIngredientList(anyRequest)

        verify(anyNonAlcoIngredientRepository).findAll(pagingCaptor.capture())
        verifyZeroInteractions(anyAlcoIngredientRepository)

        assertEquals(pagingCaptor.firstValue.pageNumber, anyRequest.pageNumber)
        assertEquals(pagingCaptor.firstValue.pageSize, anyRequest.pageSize)

        assertEquals(anyRequest, nonAlcoIngredientList.pagingInfo)
        assertTrue(nonAlcoIngredientList.list.isEmpty())
    }
}