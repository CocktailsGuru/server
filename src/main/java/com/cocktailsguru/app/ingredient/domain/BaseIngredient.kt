package com.cocktailsguru.app.ingredient.domain

import javax.persistence.*

@MappedSuperclass
abstract class BaseIngredient(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        open val id: Long,
        open var name: String,
        @Column(name = "nameGrouped")
        open var nameGrouped: String,
        @Column(name = "desc")
        open var description: String,
        @Column(name = "imgFileName")
        open var imageName: String,
        @Column(name = "numShowed")
        open var numShowed: Int,
        @OneToOne
        @JoinColumn(name = "categoryFK")
        open var ingredientType: IngredientType
)