package com.cocktailsguru.app.common.domain

import java.time.format.DateTimeFormatter

class CocktailDateTimeFormatter {
    companion object {
        val FORMATTER = DateTimeFormatter.ofPattern("YYYY-MM-dd HH:mm:ss")!!
    }
}