package com.cocktailsguru.app.cocktail.domain

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity(name = "coctail_glass")
data class CocktailGlass(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private val id: Long,
        private val name: String
)