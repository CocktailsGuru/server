package com.cocktailsguru.app.common.dto

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(value = HttpStatus.UNAUTHORIZED, reason = "Provided user credentials are invalid")
class UnauthorizedException() : RuntimeException() {
}