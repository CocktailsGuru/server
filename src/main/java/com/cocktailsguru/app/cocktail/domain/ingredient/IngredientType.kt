package com.cocktailsguru.app.cocktail.domain.ingredient

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity(name = "coctail_ingred_category")
data class IngredientType(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Int,
        var name: String
)
