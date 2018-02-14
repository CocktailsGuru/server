package com.cocktailsguru.app.staticfiles

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.EnableWebMvc
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter

@Configuration
@EnableWebMvc
open class CocktailsWebMvcConfiguration : WebMvcConfigurerAdapter() {

    companion object {
        const val IMAGES_PATH = "/images/**"
    }

    @Value("\${cocktails.resource.images}")
    private lateinit var imagesResourceLocation: String

    final override fun addResourceHandlers(registry: ResourceHandlerRegistry) {
        registry.addResourceHandler(IMAGES_PATH)
                .addResourceLocations(imagesResourceLocation)
    }
}
