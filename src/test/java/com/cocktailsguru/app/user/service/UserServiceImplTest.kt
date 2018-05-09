package com.cocktailsguru.app.user.service

import com.cocktailsguru.app.user.domain.FbUser
import com.cocktailsguru.app.user.domain.GoogleUser
import com.cocktailsguru.app.user.repository.FbUserRepository
import com.cocktailsguru.app.user.repository.GoogleUserRepository
import com.cocktailsguru.app.user.repository.UserTokenRepository
import com.nhaarman.mockito_kotlin.*
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner
import java.util.*
import kotlin.test.assertNotNull
import kotlin.test.assertNull
import kotlin.test.assertTrue

@RunWith(MockitoJUnitRunner::class)
class UserServiceImplTest {
    private val anyFbUserRepository = mock<FbUserRepository>()
    private val anyGoogleUserRepository = mock<GoogleUserRepository>()
    private val userTokeRepository = mock<UserTokenRepository>()

    private val userService: UserService = UserServiceImpl(anyFbUserRepository, anyGoogleUserRepository, userTokeRepository)

    private val anyId = 123L

    @Test
    fun givenNoUserWhenRequestingUserByIdShouldReturnNull() {
        whenever(anyFbUserRepository.findById(any())).thenReturn(Optional.empty())
        whenever(anyGoogleUserRepository.findById(any())).thenReturn(Optional.empty())

        assertNull(userService.findUserById(anyId))

        verify(anyFbUserRepository).findById(anyId)
        verify(anyGoogleUserRepository).findById(anyId)
    }

    @Test
    fun givenExistingFbUserOnlyWhenRequestingUserByIdShouldReturnFbUser() {
        val anyFbUser = mock<FbUser>()

        whenever(anyFbUserRepository.findById(anyId)).thenReturn(Optional.of(anyFbUser))

        val foundUser = userService.findUserById(anyId)
        assertNotNull(foundUser)
        assertTrue { foundUser is FbUser }

        verify(anyFbUserRepository).findById(anyId)
        verifyZeroInteractions(anyGoogleUserRepository)
    }


    @Test
    fun givenExistingGoogleUserOnlyWhenRequestingUserByIdShouldReturnGoogleUser() {
        val anyGoogleUser = mock<GoogleUser>()

        whenever(anyFbUserRepository.findById(any())).thenReturn(Optional.empty())
        whenever(anyGoogleUserRepository.findById(anyId)).thenReturn(Optional.of(anyGoogleUser))

        val foundUser = userService.findUserById(anyId)
        assertNotNull(foundUser)
        assertTrue { foundUser is GoogleUser }

        verify(anyFbUserRepository).findById(anyId)
        verify(anyGoogleUserRepository).findById(anyId)
    }
}
