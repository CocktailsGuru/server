package com.cocktailsguru.app.cocktail.service

import com.cocktailsguru.app.cocktail.domain.Cocktail
import com.cocktailsguru.app.cocktail.repository.CocktailRepository
import com.cocktailsguru.app.common.domain.PagingInfo
import com.nhaarman.mockito_kotlin.*
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertNull
import kotlin.test.assertTrue

@RunWith(MockitoJUnitRunner::class)
class CocktailServiceImplTest {

    private val anyCocktailRepository = mock<CocktailRepository>()

    private val anyCocktail = mock<Cocktail>()

    private val cocktailService: CocktailService = CocktailServiceImpl(anyCocktailRepository)


    @Test
    fun shouldReturnRepositoryResponse() {
        val anyId = 1L
        whenever(anyCocktailRepository.findOne(anyId)).thenReturn(anyCocktail)
        assertEquals(anyCocktail, cocktailService.getCocktailDetail(anyId))
        verify(anyCocktailRepository).findOne(anyId)
    }

    @Test
    fun shouldReturnNullOnNoResponseFromRepository() {
        val anyId = 1L
        whenever(anyCocktailRepository.findOne(anyId)).thenReturn(null)
        assertNull(cocktailService.getCocktailDetail(anyId))
        verify(anyCocktailRepository).findOne(anyId)
    }

    @Test
    fun shouldReturnEmptyListOnNoResponseFromRepository() {
        val anyPageSize = 10
        val anyPageNumber = 20
        val anyRequest = mock<PagingInfo>()
        whenever(anyRequest.pageNumber).thenReturn(anyPageNumber)
        whenever(anyRequest.pageSize).thenReturn(anyPageSize)
        val anyPagingCaptor = argumentCaptor<PageRequest>()
        val anyPageResponse = mock<Page<Cocktail>>()
        whenever(anyPageResponse.content).thenReturn(ArrayList())
        whenever(anyCocktailRepository.findAll(any<PageRequest>())).thenReturn(anyPageResponse)

        val cocktailList = cocktailService.getCocktailList(anyRequest)
        verify(anyCocktailRepository).findAll(anyPagingCaptor.capture())
        assertTrue(cocktailList.list.isEmpty())
        assertEquals(anyRequest, cocktailList.pagingInfo)
        assertEquals(anyPageSize, anyPagingCaptor.firstValue.pageSize)
        assertEquals(anyPageNumber, anyPagingCaptor.firstValue.pageNumber)
    }

    @Test
    fun shouldReturnCocktailsFromRepositoryWhenRequestingList() {
        val anyPageSize = 10
        val anyPageNumber = 20
        val anyRequest = mock<PagingInfo>()
        val anyCocktail = mock<Cocktail>()
        whenever(anyRequest.pageNumber).thenReturn(anyPageNumber)
        whenever(anyRequest.pageSize).thenReturn(anyPageSize)
        val anyPagingCaptor = argumentCaptor<PageRequest>()
        val anyPageResponse = mock<Page<Cocktail>>()
        whenever(anyPageResponse.content).thenReturn(listOf(anyCocktail))
        whenever(anyCocktailRepository.findAll(any<PageRequest>())).thenReturn(anyPageResponse)

        val cocktailList = cocktailService.getCocktailList(anyRequest)
        verify(anyCocktailRepository).findAll(anyPagingCaptor.capture())
        assertFalse(cocktailList.list.isEmpty())
        assertEquals(anyCocktail, cocktailList.list.get(0))
        assertEquals(anyRequest, cocktailList.pagingInfo)
        assertEquals(anyPageSize, anyPagingCaptor.firstValue.pageSize)
        assertEquals(anyPageNumber, anyPagingCaptor.firstValue.pageNumber)
    }
}