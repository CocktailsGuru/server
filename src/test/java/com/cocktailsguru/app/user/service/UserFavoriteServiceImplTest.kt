package com.cocktailsguru.app.user.service

import com.cocktailsguru.app.cocktail.domain.CocktailObjectType
import com.cocktailsguru.app.cocktail.service.CocktailService
import com.cocktailsguru.app.picture.service.PictureService
import com.cocktailsguru.app.user.domain.favorite.UserFavorite
import com.cocktailsguru.app.user.repository.UserFavoriteRepository
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

@RunWith(MockitoJUnitRunner::class)
class UserFavoriteServiceImplTest {
    private val anyUserFavoriteRepository = mock<UserFavoriteRepository>()
    private val anyUserService = mock<UserService>()
    private val anyCocktailService = mock<CocktailService>()
    private val anyPictureService = mock<PictureService>()

    private val userFavoriteService: UserFavoriteService = UserFavoriteServiceImpl(anyUserFavoriteRepository, anyUserService, anyCocktailService, anyPictureService)

    @Test
    fun givenUserFavoritePresentWhenRequestingUserFavoriteShouldReturnOneItem() {
        val anyObject = mock<CocktailObjectType>()
        val anyId = 123L
        val anyUserFavorite = mock<UserFavorite>()
        whenever(anyUserFavoriteRepository.findByObjectTypeAndObjectForeignKey(anyObject, anyId)).thenReturn(listOf(anyUserFavorite))

        val favoriteObjects = userFavoriteService.getFavoriteObjects(anyObject, anyId)

        assertFalse(favoriteObjects.isEmpty())
        assertEquals(1, favoriteObjects.size)
        assertEquals(anyUserFavorite, favoriteObjects.get(0))
        verify(anyUserFavoriteRepository).findByObjectTypeAndObjectForeignKey(anyObject, anyId)
    }


    @Test
    fun giveNoUserFavoriteInRepositoryWhenRequestingUserFavoriteShouldReturnEmptyList() {
        val anyObject = mock<CocktailObjectType>()
        val anyId = 123L
        whenever(anyUserFavoriteRepository.findByObjectTypeAndObjectForeignKey(anyObject, anyId)).thenReturn(listOf())

        val favoriteObjects = userFavoriteService.getFavoriteObjects(anyObject, anyId)

        assertTrue(favoriteObjects.isEmpty())
        verify(anyUserFavoriteRepository).findByObjectTypeAndObjectForeignKey(anyObject, anyId)
    }
}