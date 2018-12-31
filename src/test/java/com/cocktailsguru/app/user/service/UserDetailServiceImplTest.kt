package com.cocktailsguru.app.user.service

import com.cocktailsguru.app.user.domain.User
import com.cocktailsguru.app.user.domain.favorite.UserFavorite
import com.cocktailsguru.app.user.domain.rating.UserRating
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import junit.framework.TestCase.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class UserDetailServiceImplTest {

    @Mock private lateinit var anyUserFavoriteService: UserFavoriteService
    @Mock private lateinit var anyUserRatingService: UserRatingService

    @InjectMocks private lateinit var userDetailService: UserDetailServiceImpl


    @Test
    fun givenUser_whenCallingGetUserDetail_shouldRetrieveDataFromFavoriteAndRatingService() {
        val anyFavorite = mock<UserFavorite>()
        val anyRating = mock<UserRating>()
        val anyUser = mock<User>()
        val anyFavoriteList = listOf(anyFavorite)
        val anyRatingList = listOf(anyRating)
        whenever(anyUserFavoriteService.getFavoritesOfUser(anyUser)).thenReturn(anyFavoriteList)
        whenever(anyUserRatingService.getRatingsOfUser(anyUser)).thenReturn(anyRatingList)

        val userDetail = userDetailService.getUserDetail(anyUser)

        verify(anyUserFavoriteService).getFavoritesOfUser(anyUser)
        verify(anyUserRatingService).getRatingsOfUser(anyUser)

        assertEquals(anyFavoriteList, userDetail.favoriteList)
        assertEquals(anyRatingList, userDetail.ratingList)
    }
}
