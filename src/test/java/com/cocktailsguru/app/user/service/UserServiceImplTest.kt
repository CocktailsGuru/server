package com.cocktailsguru.app.user.service

import com.cocktailsguru.app.user.domain.FbUser
import com.cocktailsguru.app.user.domain.GoogleUser
import com.cocktailsguru.app.user.repository.FbUserRepository
import com.cocktailsguru.app.user.repository.GoogleUserRepository
import com.nhaarman.mockito_kotlin.*
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner
import kotlin.test.assertNotNull
import kotlin.test.assertNull
import kotlin.test.assertTrue

@RunWith(MockitoJUnitRunner::class)
class UserServiceImplTest {
    private val anyFbUserRepository = mock<FbUserRepository>()
    private val anyGoogleUserRepository = mock<GoogleUserRepository>()

    private val userService: UserService = UserServiceImpl(anyFbUserRepository, anyGoogleUserRepository)

    private val anyId = 123L

    @Test
    fun givenNoUserWhenRequestingUserByIdShouldReturnNull() {
        whenever(anyFbUserRepository.findOne(any())).thenReturn(null)
        whenever(anyGoogleUserRepository.findOne(any())).thenReturn(null)

        assertNull(userService.findUserById(anyId))

        verify(anyFbUserRepository).findOne(anyId)
        verify(anyGoogleUserRepository).findOne(anyId)
    }

    @Test
    fun givenExistingFbUserOnlyWhenRequestingUserByIdShouldReturnFbUser() {
        val anyFbUser = mock<FbUser>()

        whenever(anyFbUserRepository.findOne(anyId)).thenReturn(anyFbUser)

        val foundUser = userService.findUserById(anyId)
        assertNotNull(foundUser)
        assertTrue { foundUser is FbUser }

        verify(anyFbUserRepository).findOne(anyId)
        verifyZeroInteractions(anyGoogleUserRepository)
    }


    @Test
    fun givenExistingGoogleUserOnlyWhenRequestingUserByIdShouldReturnGoogleUser() {
        val anyGoogleUser = mock<GoogleUser>()

        whenever(anyFbUserRepository.findOne(any())).thenReturn(null)
        whenever(anyGoogleUserRepository.findOne(anyId)).thenReturn(anyGoogleUser)

        val foundUser = userService.findUserById(anyId)
        assertNotNull(foundUser)
        assertTrue { foundUser is GoogleUser }

        verify(anyFbUserRepository).findOne(anyId)
        verify(anyGoogleUserRepository).findOne(anyId)
    }
}
