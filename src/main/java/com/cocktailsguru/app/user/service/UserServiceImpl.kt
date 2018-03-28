package com.cocktailsguru.app.user.service

import com.cocktailsguru.app.user.domain.*
import com.cocktailsguru.app.user.domain.registration.UserRegistrationResult
import com.cocktailsguru.app.user.domain.registration.UserRegistrationResultType
import com.cocktailsguru.app.user.repository.FbUserRepository
import com.cocktailsguru.app.user.repository.GoogleUserRepository
import com.cocktailsguru.app.user.repository.UserTokenRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.time.Clock
import java.time.LocalDateTime
import java.util.*

@Service
class UserServiceImpl @Autowired constructor(
        private val fbUserRepository: FbUserRepository,
        private val googleUserRepository: GoogleUserRepository,
        private val userTokenRepository: UserTokenRepository
) : UserService {


    override fun verifyUser(verificationRequest: UserTokenToVerify): User? {
        val userToken = userTokenRepository.findFirstByUserIdAndToken(verificationRequest.userId, verificationRequest.token)

        if (userToken == null || !userToken.valid) {
            return null
        }
        return findUserById(userToken.userId)
    }

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
            val existingToken = userTokenRepository.findFirstByUserIdAndValidTrue(existingUser.id)
            val userToken = existingToken ?: createNewUserToken(existingUser)
            UserRegistrationResult(existingUser, userToken, UserRegistrationResultType.EXISTING_USER)
        } else {
            val newUser = when (registrationRequest.registrationType) {
                UserRegistrationType.FB -> registerFbUser(registrationRequest)
                UserRegistrationType.GOOGLE -> registerGoogleUser(registrationRequest)
            }
            UserRegistrationResult(
                    newUser,
                    createNewUserToken(newUser),
                    UserRegistrationResultType.NEW_REGISTRATION
            )
        }
    }

    private fun createNewUserToken(user: User): UserToken {
        val newToken = UserToken(
                0,
                user.id,
                UUID.randomUUID().toString(),
                true
        )
        return userTokenRepository.save(newToken)
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