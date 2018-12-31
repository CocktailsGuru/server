package com.cocktailsguru.app

import com.cocktailsguru.app.user.domain.User
import com.cocktailsguru.app.user.domain.UserTokenToVerify
import com.cocktailsguru.app.user.service.UserService
import com.nhaarman.mockito_kotlin.*
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary
import org.springframework.context.annotation.Profile

@Configuration
class AuthenticatedIntegrationConfiguration {

    @Bean
    @Primary
    fun userService(userService: UserService): UserService {
        val userServiceSpy = spy(userService)
        val anyUser = mock<User>()
        doReturn(anyUser).whenever(userServiceSpy).verifyUser(eq(UserTokenToVerify(MockRequestUtils.userId, MockRequestUtils.token)))
        return userServiceSpy
    }
}
