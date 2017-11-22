package com.cocktailsguru.app.cocktail.domain

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
        var imageName: String
)
