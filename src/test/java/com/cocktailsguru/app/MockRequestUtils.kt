package com.cocktailsguru.app

import com.cocktailsguru.app.verification.service.UserVerificationService
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder

object MockRequestUtils {

    val userId = 6L
    val token = "adminToken"

    fun addAdminHeaders(requestBuilder: MockHttpServletRequestBuilder): MockHttpServletRequestBuilder {
        return requestBuilder.header(UserVerificationService.USER_ID_HEADER, userId)
                .header(UserVerificationService.TOKEN_HEADER, token)
    }
}