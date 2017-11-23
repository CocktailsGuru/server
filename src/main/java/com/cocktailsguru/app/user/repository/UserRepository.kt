package com.cocktailsguru.app.user.repository

import com.cocktailsguru.app.user.domain.User
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository : CrudRepository<User, Long>