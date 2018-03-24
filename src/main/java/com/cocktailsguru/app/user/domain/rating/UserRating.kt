package com.cocktailsguru.app.user.domain.rating

import com.cocktailsguru.app.cocktail.domain.CocktailObjectType
import com.cocktailsguru.app.user.domain.User
import java.time.LocalDateTime
import javax.persistence.*

@Entity(name = "coctail_user_rating")
data class UserRating(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long,
        @Enumerated(EnumType.ORDINAL)
        @Column(name = "objectTypeFK")
        val objectType: CocktailObjectType,
        @Column(name = "objectFK")
        val objectForeignKey: Long,
        @OneToOne
        @JoinColumn(name = "userId")
        val user: User,
        @Enumerated(EnumType.ORDINAL)
        var rating: RatingType,
        @Column(name = "ct")
        val createdTime: LocalDateTime
)