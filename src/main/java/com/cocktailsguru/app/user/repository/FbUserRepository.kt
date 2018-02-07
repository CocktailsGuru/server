package com.cocktailsguru.app.user.repository

import com.cocktailsguru.app.user.domain.FbUser
import org.springframework.stereotype.Repository
import javax.transaction.Transactional

@Repository
@Transactional
interface FbUserRepository : UserRepository<FbUser>