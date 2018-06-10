package com.cocktailsguru.app

import com.cocktailsguru.app.verification.service.UserVerificationService
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder

object MockRequestUtils {
    fun addAdminHeaders(requestBuilder: MockHttpServletRequestBuilder): MockHttpServletRequestBuilder {
        return requestBuilder.header(UserVerificationService.USER_ID_HEADER, 6)
                .header(UserVerificationService.TOKEN_HEADER, "adminToken")
    }
}