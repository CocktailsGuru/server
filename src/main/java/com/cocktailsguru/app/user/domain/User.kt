package com.cocktailsguru.app.user.domain

import javax.persistence.*

@Entity
@Table(name = "coctail_user")
data class User(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long,
        @Column(name = "userID")
        val externalUserId: String,
        @Column(name = "userName")
        var name: String,
        @Enumerated(EnumType.ORDINAL)
        @Column(name = "userGender")
        val gender: Gender,
        @Column(name = "userImage")
        var image: String,
        @Column(name = "userAge")
        var age: Int,
        @Column(name = "userCountryCode")
        var countryCode: String,
        var numSessions: Int,
        var numPictures: Int,
        var numPicturesFav: Int,
        var numComments: Int,
        var numFollowers: Int,
        var numFollowing: Int,
        var numCocktailsFav: Int,
        @Column(name = "numCocktailsRat")
        var numCocktailsRated: Int,
        var numShown: Int,
        //TODO rename
        var lastDate: String
)

