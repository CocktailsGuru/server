package com.cocktailsguru.app.picture.domain

import com.cocktailsguru.app.cocktail.domain.CocktailObjectType
import com.cocktailsguru.app.user.domain.User
import java.time.LocalDateTime
import javax.persistence.*

@Entity(name = "coctail_user_picture")
data class Picture(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long,
        @Enumerated(value = EnumType.ORDINAL)
        @Column(name = "objectTypeFK")
        val objectType: CocktailObjectType,
        @Column(name = "objectFK")
        val objectForeignKey: Long,
        val objectName: String,
        val description: String,
        val fileName: String,
        @OneToOne(optional = false, fetch = FetchType.LAZY)
        @JoinColumn(name = "userId")
        val authorUser: User,
        var numLikes: Int,
        @Column(name = "numLikesDis")
        var numDisLikes: Int,
        var numFav: Int,
        var numComments: Int,
        var numShowed: Int,
        @Column(name = "ut")
        var updateTime: LocalDateTime,
        @Column(name = "ct")
        var createdTime: LocalDateTime
)