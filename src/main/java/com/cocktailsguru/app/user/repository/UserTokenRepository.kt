package com.cocktailsguru.app.user.repository

import com.cocktailsguru.app.user.domain.UserToken
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import javax.transaction.Transactional

@Repository
@Transactional
interface UserTokenRepository : CrudRepository<UserToken, Long> {
    fun findFirstByUserIdAndToken(userId: Long, token: String): UserToken?

    fun findFirstByUserIdAndValidTrue(userId: Long): UserToken?
}