package com.cocktailsguru.app.ingredient.domain

import javax.persistence.Entity

@Entity(name = "coctail_ingred_alco")
data class AlcoIngredient(
        override val id: Long,
        override var name: String,
        override var nameGrouped: String,
        override var description: String,
        override var imageName: String,
        override var numShowed: Int,
        override var ingredientType: IngredientType,
        var voltage: Int) : BaseIngredient(id, name, nameGrouped, description, imageName, numShowed, ingredientType)