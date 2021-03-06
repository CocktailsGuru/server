package com.cocktailsguru.app.cocktail.domain

import com.cocktailsguru.app.ingredient.domain.IngredientCocktail
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
        var alcoholVolume: Double,
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
        var ingredientList: List<IngredientCocktail>,
        @ManyToMany(cascade = [(CascadeType.ALL)])
        @JoinTable(
                name = "coctail_similar",
                joinColumns = [(JoinColumn(name = "cocktail_one", referencedColumnName = "id"))],
                inverseJoinColumns = [(JoinColumn(name = "cocktail_two", referencedColumnName = "id"))])
        var similarCocktailList: List<Cocktail>,
        var numRating1: Int,
        var numRating2: Int,
        var numRating3: Int,
        var numRating4: Int,
        var numRating5: Int,
        var numPictures: Int,
        var numComments: Int,
        var numShowed: Int,
        @Column(name = "numLikes")
        var numOfFavorite: Long,
        @Transient
        var calculatedRating: Double
) {
    override fun toString(): String {
        return "Cocktail(id=$id, name='$name', totalVolume=$totalVolume, alcoVolume=$alcoVolume, nonAlcoVolume=$nonAlcoVolume, instructions='$instructions', garnish='$garnish', description='$description', imageName='$imageName', glass=$glass, method=$method, ingredientList=$ingredientList, similarCocktailList=${similarCocktailList.map { it -> it.id }})"
    }

    @PostLoad
    private fun postLoad() {
        val countOfRatings = numRating1 + numRating2 + numRating3 + numRating4 + numRating5
        calculatedRating = if (countOfRatings > 0) {
            (1.0 * numRating1 + 2.0 * numRating2 + 3.0 * numRating3 + 4.0 * numRating4 + 5.0 * numRating5) / countOfRatings
        } else {
            0.0
        }
    }
}
