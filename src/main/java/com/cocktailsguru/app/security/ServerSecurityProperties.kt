package com.cocktailsguru.app.security

import org.springframework.boot.autoconfigure.security.SecurityProperties
import org.springframework.boot.context.properties.ConfigurationProperties
import java.util.*

@ConfigurationProperties(prefix = "security")
data class ServerSecurityProperties(val users: List<SecurityProperties.User> = ArrayList())
