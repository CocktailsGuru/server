package com.cocktailsguru.app.verification.service

import com.cocktailsguru.app.common.dto.UnauthorizedException
import com.cocktailsguru.app.user.domain.User
import com.cocktailsguru.app.user.domain.UserTokenToVerify
import com.cocktailsguru.app.user.repository.UserTokenRepository
import com.cocktailsguru.app.user.service.UserService
import com.cocktailsguru.app.verification.service.UserVerificationService.Companion.TOKEN_HEADER
import com.cocktailsguru.app.verification.service.UserVerificationService.Companion.USER_ID_HEADER
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import javax.servlet.http.HttpServletRequest

@Service
class UserVerificationServiceImpl @Autowired constructor(
        private val userTokenRepository: UserTokenRepository,
        private val userService: UserService
) : UserVerificationService {

    @Autowired
    private lateinit var request: HttpServletRequest


    override fun getLoggedUser(): User {
        val foundUser = userService.verifyUser(getUserTokenFromRequest())

        if (foundUser != null) {
            return foundUser
        } else {
            throw throw UnauthorizedException()
        }
    }

    override fun verifyUser(): Boolean {
        val userTokenToVerify = getUserTokenFromRequest()
        val userToken = userTokenRepository.findFirstByUserIdAndToken(userTokenToVerify.userId, userTokenToVerify.token)

        return userToken != null && userToken.valid
    }

    private fun getUserTokenFromRequest(): UserTokenToVerify {
        val userIdHeader = request.getHeader(USER_ID_HEADER)
        val tokenHeader = request.getHeader(TOKEN_HEADER)

        if (userIdHeader == null || tokenHeader == null) {
            throw UnauthorizedException()
        }

        return UserTokenToVerify(userIdHeader.toLong(), tokenHeader)
    }
}