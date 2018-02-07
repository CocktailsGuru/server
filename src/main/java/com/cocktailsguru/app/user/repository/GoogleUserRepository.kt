package com.cocktailsguru.app.user.repository

import com.cocktailsguru.app.user.domain.GoogleUser
import org.springframework.stereotype.Repository
import javax.transaction.Transactional

@Repository
@Transactional
interface GoogleUserRepository : UserRepository<GoogleUser>