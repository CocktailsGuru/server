package com.cocktailsguru.app.cocktail.domain

import com.cocktailsguru.app.cocktail.domain.ingredient.AlcoIngredientCocktail
import com.cocktailsguru.app.cocktail.domain.ingredient.NonAlcoIngredientCocktail
import javax.persistence.*

@Entity(name = "coctail")
data class Cocktail(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long,
        var name: String,
        @Column(name = "volumeTotal")
        var totalVolume: Int,
        @Column(name = "volumeAlko")
        var alcoVolume: Int,
        @Column(name = "volumeAlkoNon")
        var nonAlcoVolume: Int,
        var instructions: String,
        var garnish: String,
        var description: String,
        @Column(name = "imgFileName")
        var imageName: String,
        @OneToOne
        @JoinColumn(name = "glassFK")
        var glass: CocktailGlass,
        @OneToOne
        @JoinColumn(name = "methodFK")
        var method: CocktailMethod,
        @OneToMany(fetch = FetchType.LAZY, mappedBy = "cocktail")
        val alcoIngredList: List<AlcoIngredientCocktail>,
        @OneToMany(fetch = FetchType.LAZY, mappedBy = "cocktail")
        val nonAlcoIngredList: List<NonAlcoIngredientCocktail>
)
