package com.cocktailsguru.app.user.domain.favorite

import com.cocktailsguru.app.cocktail.domain.CocktailObjectType
import com.cocktailsguru.app.user.domain.User
import javax.persistence.*

@Entity(name = "coctail_user_favorite")
data class UserFavorite(
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
        val user: User
)