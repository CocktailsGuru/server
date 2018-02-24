package com.cocktailsguru.app.user.repository

import com.cocktailsguru.app.user.domain.User
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.NoRepositoryBean
import org.springframework.stereotype.Repository
import javax.transaction.Transactional

@NoRepositoryBean
@Repository
@Transactional
interface UserRepository<T : User> : CrudRepository<T, Long> {
    fun findFirstByExternalUserId(externalUserId: String): T?
}