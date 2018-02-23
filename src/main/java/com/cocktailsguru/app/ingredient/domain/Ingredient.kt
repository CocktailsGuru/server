package com.cocktailsguru.app.ingredient.domain

import javax.persistence.*

@Entity(name = "ingredient")
data class Ingredient(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long,
        var name: String,
        var nameGrouped: String,
        var voltage: String,
        @Column(name = "desc")
        var description: String,
        @Column(name = "imgFileName")
        var imageName: String,
        @Column(name = "numShowed")
        var numShowed: Int,
        @OneToOne
        @JoinColumn(name = "categoryFK")
        var ingredientCategoryType: IngredientCategoryType,
        @Column(name = "ingredType")
        @Enumerated(EnumType.STRING)
        var ingredientType: IngredientType
)