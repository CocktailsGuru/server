package com.cocktailsguru.app.security

import com.cocktailsguru.app.utils.loggerFor
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.security.SecurityProperties
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.authentication.configurers.provisioning.InMemoryUserDetailsManagerConfigurer
import org.springframework.security.config.annotation.authentication.configurers.provisioning.UserDetailsManagerConfigurer
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.http.SessionCreationPolicy


@Configuration
@EnableWebSecurity
@EnableConfigurationProperties(ServerSecurityProperties::class)
open class SecurityConfiguration @Autowired constructor(
        private val securityProperties: ServerSecurityProperties
) : WebSecurityConfigurerAdapter() {

    private val logger = loggerFor(javaClass)

    override fun configure(http: HttpSecurity) {
        http.headers()
                .and().csrf().disable().httpBasic()
                .and().exceptionHandling()
                .and().authorizeRequests().anyRequest().authenticated()

        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)

        http.authorizeRequests().antMatchers("/v2/api-docs").hasAnyRole(Roles.MODERATOR.name)
        http.authorizeRequests().antMatchers("/health").hasAnyRole(Roles.MODERATOR.name)
    }

    override fun configure(auth: AuthenticationManagerBuilder) {
        securityProperties.users.forEach { configureUser(auth.inMemoryAuthentication(), it) }
    }

    private fun configureUser(
            authentication: UserDetailsManagerConfigurer<AuthenticationManagerBuilder, InMemoryUserDetailsManagerConfigurer<AuthenticationManagerBuilder>>,
            user: SecurityProperties.User
    ) {
        authentication.withUser(user.name)
                .password(user.password)
                .roles(*user.role.toTypedArray())
        logger.info("Created user {} with roles {}", user.name, user.role)
    }
}