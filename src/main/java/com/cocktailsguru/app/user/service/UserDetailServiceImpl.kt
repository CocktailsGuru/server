package com.cocktailsguru.app.user.service

import com.cocktailsguru.app.user.domain.User
import com.cocktailsguru.app.user.domain.detail.UserDetail
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class UserDetailServiceImpl @Autowired constructor(
        private val userFavoriteService: UserFavoriteService,
        private val userRatingService: UserRatingService
) : UserDetailService {

    override fun getUserDetail(user: User): UserDetail {
        return UserDetail(
                userFavoriteService.getFavoritesOfUser(user),
                userRatingService.getRatingsOfUser(user)
        )
    }
}