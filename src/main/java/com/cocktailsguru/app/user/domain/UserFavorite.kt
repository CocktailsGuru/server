package com.cocktailsguru.app.user.domain

import com.cocktailsguru.app.cocktail.domain.CocktailObjectType
import javax.persistence.*

@Entity
@Table(name = "coctail_user_favorite")
data class UserFavorite(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long,
        @Enumerated(EnumType.ORDINAL)
        @Column(name = "objectTypeFK")
        val objectType: CocktailObjectType,
        @Column(name = "objectFK")
        val objectForeignKey: Long
//        @OneToOne
//        @JoinColumn(name = "userFK")
//        val user: User
)