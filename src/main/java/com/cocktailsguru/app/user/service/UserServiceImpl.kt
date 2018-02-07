package com.cocktailsguru.app.user.service

import com.cocktailsguru.app.user.domain.FbUser
import com.cocktailsguru.app.user.domain.GoogleUser
import com.cocktailsguru.app.user.domain.User
import com.cocktailsguru.app.user.repository.FbUserRepository
import com.cocktailsguru.app.user.repository.GoogleUserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class UserServiceImpl @Autowired constructor(
        private val fbUserRepository: FbUserRepository,
        private val googleUserRepository: GoogleUserRepository
) : UserService {

    override fun findGoogleUserById(id: Long): GoogleUser? {
        return googleUserRepository.findOne(id)
    }

    override fun findFbUserById(id: Long): FbUser? {
        return fbUserRepository.findOne(id)
    }

    override fun findUserById(id: Long): User? {
        return findFbUserById(id) ?: findGoogleUserById(id)
    }
}