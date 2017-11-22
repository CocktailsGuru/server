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
        var alcoIngredList: List<AlcoIngredientCocktail>,
        @OneToMany(fetch = FetchType.LAZY, mappedBy = "cocktail")
        var nonAlcoIngredList: List<NonAlcoIngredientCocktail>,
        @ManyToMany(cascade = arrayOf(CascadeType.ALL))
        @JoinTable(
                name = "coctail_similar",
                joinColumns = arrayOf(JoinColumn(name = "cocktail_one", referencedColumnName = "id")),
                inverseJoinColumns = arrayOf(JoinColumn(name = "cocktail_two", referencedColumnName = "id")))
        var similarCocktailList: List<Cocktail>
) {
    override fun toString(): String {
        return "Cocktail(id=$id, name='$name', totalVolume=$totalVolume, alcoVolume=$alcoVolume, nonAlcoVolume=$nonAlcoVolume, instructions='$instructions', garnish='$garnish', description='$description', imageName='$imageName', glass=$glass, method=$method, alcoIngredList=$alcoIngredList, nonAlcoIngredList=$nonAlcoIngredList, similarCocktailList=${similarCocktailList.map { it -> it.id }})"
    }
}
