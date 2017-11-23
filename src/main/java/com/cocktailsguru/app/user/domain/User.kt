package com.cocktailsguru.app.user.domain

import javax.persistence.*

@Entity
@Table(name = "coctail_user")
data class User(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long,
        @Column(name = "userID")
        val userId: String,
        @Column(name = "userEmail")
        var email: String,
        @Column(name = "userName")
        var name: String,
        @Enumerated(EnumType.ORDINAL)
        @Column(name = "userGender")
        var gender: Gender,
        @Column(name = "userImage")
        var image: String
)

