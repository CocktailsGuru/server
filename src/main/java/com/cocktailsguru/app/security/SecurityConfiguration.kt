package com.cocktailsguru.app.security

import com.cocktailsguru.app.utils.loggerFor
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.actuate.autoconfigure.security.servlet.EndpointRequest
import org.springframework.boot.autoconfigure.security.SecurityProperties
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.authentication.configurers.provisioning.InMemoryUserDetailsManagerConfigurer
import org.springframework.security.config.annotation.authentication.configurers.provisioning.UserDetailsManagerConfigurer
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.password.NoOpPasswordEncoder




@Configuration
@EnableWebSecurity
@EnableConfigurationProperties(ServerSecurityProperties::class)
class SecurityConfiguration @Autowired constructor(
        private val securityProperties: ServerSecurityProperties
) : WebSecurityConfigurerAdapter() {

    @Suppress("DEPRECATION")
    @Bean
    fun passwordEncoder(): NoOpPasswordEncoder {
        return NoOpPasswordEncoder.getInstance() as NoOpPasswordEncoder
    }

    private val logger = loggerFor(javaClass)

    override fun configure(http: HttpSecurity) {
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)

        http.authorizeRequests()
                .requestMatchers(EndpointRequest.toAnyEndpoint()).hasRole(Roles.MODERATOR.name)
                .anyRequest().authenticated()
                .antMatchers("/**").hasRole(Roles.MODERATOR.name)
                .and().csrf().disable()
                .httpBasic()
                .and().exceptionHandling()

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
                .roles(*user.roles.toTypedArray())
        logger.info("Created user {} with roles {}", user.name, user.roles)
    }
}