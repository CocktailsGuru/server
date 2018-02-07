package com.cocktailsguru.app.user.domain

import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table(name = "coctail_user")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "registrationType")
abstract class User(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        open val id: Long,
        @Column(name = "userID")
        open val externalUserId: String,
        @Column(name = "userName")
        open var name: String,
        @Enumerated(EnumType.ORDINAL)
        @Column(name = "userGender")
        open val gender: Gender,
        @Column(name = "userImage")
        open var image: String,
        @Column(name = "userCountryCode")
        open var countryCode: String,
        open var numPictures: Int,
        open var numPicturesFav: Int,
        open var numComments: Int,
        open var numFollowers: Int,
        open var numFollowing: Int,
        open var numCocktailsFav: Int,
        @Column(name = "numCocktailsRat")
        open var numCocktailsRated: Int,
        open var numShown: Int,
        open var lastDate: LocalDateTime
)

