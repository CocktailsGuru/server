package com.cocktailsguru.app.staticfiles

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Configuration
import org.springframework.http.CacheControl
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter
import java.util.concurrent.TimeUnit

@Configuration
open class CocktailsWebMvcConfiguration : WebMvcConfigurerAdapter() {

    companion object {
        const val ASSETS_PATH = "/assets/**"
    }

    @Value("\${cocktails.resource.assets}")
    private lateinit var assetsResourceLocation: String

    final override fun addResourceHandlers(registry: ResourceHandlerRegistry) {
        registry.addResourceHandler(ASSETS_PATH)
                .setCacheControl(CacheControl.maxAge(10, TimeUnit.DAYS))
                .addResourceLocations(assetsResourceLocation)
    }
}
