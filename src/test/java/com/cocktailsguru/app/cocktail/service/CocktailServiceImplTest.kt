package com.cocktailsguru.app.cocktail.service

import com.cocktailsguru.app.cocktail.domain.Cocktail
import com.cocktailsguru.app.cocktail.repository.CocktailRepository
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner
import kotlin.test.assertEquals
import kotlin.test.assertNull

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
}