package com.charlie.tickets.infrastructure.web.filters

import com.charlie.tickets.domain.models.User
import com.charlie.tickets.infrastructure.persistence.UserRepository
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.oauth2.jwt.Jwt
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter
import java.util.UUID

@Component
class UserProvisioningFilter(
    private val userRepository: UserRepository
) : OncePerRequestFilter() {

    override fun doFilterInternal(
        request: jakarta.servlet.http.HttpServletRequest,
        response: jakarta.servlet.http.HttpServletResponse,
        filterChain: jakarta.servlet.FilterChain
    ) {
        // This filter is a placeholder for user provisioning logic.
        // In a real application, you would implement the logic to provision users here.
        val auth = SecurityContextHolder.getContext().authentication
        if (auth != null && auth.isAuthenticated && auth.principal is Jwt) {
            val jwt = auth.principal as Jwt

            val keycloakId: UUID = UUID.fromString(jwt.subject)
            // Check if the user exists in the database.
            if (!userRepository.existsById(keycloakId)) {
                val user = User(
                    id = keycloakId,
                    name = jwt.claims.getValue("preferred_username") as String,
                    email = jwt.claims.getValue("email") as String,
                )

                userRepository.save(user)
            }
        }

        // For now, just continue the filter chain.
        filterChain.doFilter(request, response)
    }
}