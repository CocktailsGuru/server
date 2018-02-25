package com.cocktailsguru.app.picture

import com.cocktailsguru.app.IntegrationTestApp
import com.cocktailsguru.app.cocktail.domain.CocktailObjectType
import com.cocktailsguru.app.picture.controller.PictureController
import com.cocktailsguru.app.picture.service.PictureService
import com.cocktailsguru.app.utils.loggerFor
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.json.JacksonJsonParser
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.transaction.annotation.Transactional
import org.springframework.util.MimeTypeUtils
import org.springframework.web.context.WebApplicationContext
import kotlin.test.assertFalse


@RunWith(SpringRunner::class)
@SpringBootTest(classes = [(IntegrationTestApp::class)])
@Transactional
open class PictureIntegrationPlayground {
    private val logger = loggerFor(javaClass)

    private lateinit var mockMvc: MockMvc

    @Autowired
    private lateinit var pictureService: PictureService

    @Autowired
    private lateinit var wac: WebApplicationContext

    @Before
    fun setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build()
    }

    @Test
    fun shouldFindPicturesForIba() {
        val ibaPictureList = pictureService.getPictureListForObject(CocktailObjectType.COCKTAIL, 16000)
        ibaPictureList.forEach {
            logger.debug("{} {}", it.authorUser.name, it.fileName)
        }
        assertFalse(ibaPictureList.isEmpty())
    }


    @Test
    fun shouldReturnNonEmptyPictureListForIba() {
        val result = mockMvc.perform(
                MockMvcRequestBuilders.get(PictureController.PICTURE_LIST_PATH)
                        .param("objectId", "16000")
                        .param("objectType", "1")
                        .contentType(MimeTypeUtils.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk)
                .andReturn()
        val responseJson = result.response.contentAsString
        val responseDto = JacksonJsonParser().parseList(responseJson)
        logger.info(responseDto.toString())
        assertFalse { responseDto.isEmpty() }
    }
}