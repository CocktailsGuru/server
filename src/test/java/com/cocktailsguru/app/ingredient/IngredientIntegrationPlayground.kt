package com.cocktailsguru.app.ingredient

import com.cocktailsguru.app.IntegrationTestApp
import com.cocktailsguru.app.common.domain.PagingInfo
import com.cocktailsguru.app.ingredient.service.IngredientService
import com.cocktailsguru.app.utils.loggerFor
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.transaction.annotation.Transactional
import kotlin.test.assertFalse
import kotlin.test.assertNotNull


@RunWith(SpringRunner::class)
@SpringBootTest(classes = [(IntegrationTestApp::class)])
@Transactional
open class IngredientIntegrationPlayground {

    private val log = loggerFor(javaClass)

    @Autowired
    lateinit var ingredientService: IngredientService

    @Test
    fun shouldReturnAnyAlcoIngredientList() {
        val alcoIngredientList = ingredientService.getAlcoIngredientList(PagingInfo(0, 10))

        assertNotNull(alcoIngredientList)
        assertFalse(alcoIngredientList.list.isEmpty())
    }


    @Test
    fun shouldReturnAnyNonAlcoIngredientList() {
        val nonAlcoIngredientList = ingredientService.getNonAlcoIngredientList(PagingInfo(0, 10))

        assertNotNull(nonAlcoIngredientList)
        assertFalse(nonAlcoIngredientList.list.isEmpty())
    }
}