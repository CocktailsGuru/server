package com.cocktailsguru.app.user.service

import com.cocktailsguru.app.user.domain.*
import com.cocktailsguru.app.user.domain.registration.UserRegistrationResult
import com.cocktailsguru.app.user.domain.registration.UserRegistrationResultType
import com.cocktailsguru.app.user.repository.FbUserRepository
import com.cocktailsguru.app.user.repository.GoogleUserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.time.Clock
import java.time.LocalDateTime

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

    override fun registerUser(registrationRequest: UserRegistrationRequest): UserRegistrationResult {
        val repository = when (registrationRequest.registrationType) {
            UserRegistrationType.FB -> fbUserRepository
            UserRegistrationType.GOOGLE -> googleUserRepository
        }

        val existingUser = repository.findFirstByExternalUserId(registrationRequest.externalUserId)

        return if (existingUser != null) {
            UserRegistrationResult(existingUser, UserRegistrationResultType.EXISTING_USER)
        } else
            UserRegistrationResult(
                    when (registrationRequest.registrationType) {
                        UserRegistrationType.FB -> registerFbUser(registrationRequest)
                        UserRegistrationType.GOOGLE -> registerGoogleUser(registrationRequest)
                    },
                    UserRegistrationResultType.NEW_REGISTRATION
            )
    }

    private fun registerFbUser(registrationRequest: UserRegistrationRequest): FbUser {
        val fbUser = FbUser(
                0,
                registrationRequest.externalUserId,
                registrationRequest.name,
                registrationRequest.gender,
                registrationRequest.image,
                registrationRequest.countryCode,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                LocalDateTime.now(Clock.systemUTC()),
                registrationRequest.language!!
        )

        return fbUserRepository.save(fbUser)
    }

    private fun registerGoogleUser(registrationRequest: UserRegistrationRequest): GoogleUser {
        val googleUser = GoogleUser(
                0,
                registrationRequest.externalUserId,
                registrationRequest.name,
                registrationRequest.gender,
                registrationRequest.image,
                registrationRequest.countryCode,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                LocalDateTime.now(Clock.systemUTC())
        )

        return googleUserRepository.save(googleUser)
    }

}