package com.cocktailsguru.app

import com.cocktailsguru.app.utils.loggerFor
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner
import java.util.*

@RunWith(MockitoJUnitRunner::class)
class SimpleTest {

    private val log = loggerFor(javaClass)

    @Test
    fun simpleFun() {
        log.info(UUID.randomUUID().toString())
    }
}
