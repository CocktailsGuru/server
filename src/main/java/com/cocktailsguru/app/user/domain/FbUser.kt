package com.cocktailsguru.app.user.domain

import java.time.LocalDateTime
import javax.persistence.Column
import javax.persistence.DiscriminatorValue
import javax.persistence.Entity

@Entity
@DiscriminatorValue("FB")
data class FbUser(
        override val id: Long,
        override val externalUserId: String,
        override var name: String,
        override val gender: Gender,
        override var image: String,
        override var countryCode: String,
        override var numPictures: Int,
        override var numPicturesFav: Int,
        override var numComments: Int,
        override var numFollowers: Int,
        override var numFollowing: Int,
        override var numCocktailsFav: Int,
        override var numCocktailsRated: Int,
        override var numShown: Int,
        override var lastDate: LocalDateTime,
        @Column(name = "userFBLanguage")
        val language: String
) : User(id, externalUserId, name, gender, image, countryCode, numPictures, numPicturesFav, numComments, numFollowers, numFollowing, numCocktailsFav, numCocktailsRated, numShown, lastDate)