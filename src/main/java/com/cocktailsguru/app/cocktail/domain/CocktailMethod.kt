package com.cocktailsguru.app.cocktail.domain

import javax.persistence.*

@Entity
@Table(name = "coctail_method")
data class CocktailMethod(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Int,
        val name: String,
        @Column(name = "desc")
        val description: String
)