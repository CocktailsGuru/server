package com.cocktailsguru.app.user.service

import com.cocktailsguru.app.IntegrationTestApp
import com.cocktailsguru.app.cocktail.domain.CocktailObjectType
import com.cocktailsguru.app.user.domain.FbUser
import com.cocktailsguru.app.user.domain.GoogleUser
import com.cocktailsguru.app.utils.loggerFor
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.transaction.annotation.Transactional
import kotlin.test.assertFalse
import kotlin.test.assertNotNull
import kotlin.test.assertNull
import kotlin.test.assertTrue

@RunWith(SpringRunner::class)
@SpringBootTest(classes = [(IntegrationTestApp::class)])
@Transactional
open class UserIntegrationPlayground {

    private val log = loggerFor(javaClass)

    @Autowired
    lateinit var userService: UserService

    @Autowired
    lateinit var userFavoriteService: UserFavoriteService


    @Test
    fun shouldFindUserById() {
        assertNull(userService.findUserById(1))

        val googleUser = userService.findUserById(7168)
        assertNotNull(googleUser)
        assertTrue { googleUser is GoogleUser }

        val fbUser = userService.findUserById(7169)
        assertNotNull(fbUser)
        assertTrue { fbUser is FbUser }
    }

    @Test
    fun shouldReturnNotNullListOfFavorite() {
        val favoriteObjects = userFavoriteService.getFavoriteObjects(CocktailObjectType.COCKTAIL, 54L)
        assertFalse(favoriteObjects.isEmpty())
    }
}