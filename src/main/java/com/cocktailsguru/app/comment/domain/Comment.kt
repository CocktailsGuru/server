package com.cocktailsguru.app.comment.domain

import com.cocktailsguru.app.cocktail.domain.CocktailObjectType
import com.cocktailsguru.app.user.domain.User
import java.time.LocalDateTime
import javax.persistence.*


@Entity(name = "coctail_user_comment")
data class Comment(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long,
        @Enumerated(value = EnumType.ORDINAL)
        @Column(name = "objectTypeFK")
        val objectType: CocktailObjectType,
        @Column(name = "objectFK")
        val objectForeignKey: Long,
        val objectName: String,
        @OneToOne(optional = false, fetch = FetchType.LAZY)
        @JoinColumn(name = "userId")
        val authorUser: User,
        val content: String,
        var numLikes: Int,
        @Column(name = "numLikesDis")
        var numDislikes: Int,
        var isVisible: Boolean,
        @Column(name = "ut")
        var updateTime: LocalDateTime,
        @Column(name = "ct")
        var createdTime: LocalDateTime
)