package com.cocktailsguru.app.staticfiles

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration
open class CocktailsWebMvcConfiguration : WebMvcConfigurer {

    companion object {
        const val ASSETS_PATH = "/assets/**"
    }

    @Value("\${cocktails.resource.assets}")
    private lateinit var assetsResourceLocation: String

    final override fun addResourceHandlers(registry: ResourceHandlerRegistry) {
        registry.addResourceHandler(ASSETS_PATH)
                .setCachePeriod(864000)
                .addResourceLocations(assetsResourceLocation)
    }
}
