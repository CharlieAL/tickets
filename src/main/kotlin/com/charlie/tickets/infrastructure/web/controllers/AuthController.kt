package com.charlie.tickets.infrastructure.web.controllers

import com.charlie.tickets.application.service.AuthService
import com.charlie.tickets.domain.models.TokenPair
import com.charlie.tickets.domain.models.User
import jakarta.validation.Valid
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.Pattern
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/auth")
class AuthController(
    private val authService: AuthService
) {
    data class AuthRequest(
        val name: String?,
        @field:Email(message = "Invalid email format")
        val email: String,
        @field:Pattern(
            regexp = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{6,}$",
            message = "Password must be at least 6 characters long and contain letters and numbers"
        )
        val password: String
    )

    data class RefreshRequest(
        val refreshToken: String
    )

    @PostMapping("/register")
    fun register(
        @Valid @RequestBody body: AuthRequest
    ): User {
        if (body.name.isNullOrBlank()) {
            throw IllegalArgumentException("Name cannot be empty")
        }
        return authService.register(
            name = body.name,
            email = body.email,
            password = body.password
        )
    }

    @PostMapping("/login")
    fun login(
        @RequestBody body: AuthRequest
    ): TokenPair {
        return authService.login(
            email = body.email,
            password = body.password
        )
    }

    @PostMapping("/refresh")
    fun refresh(
        @RequestBody body: RefreshRequest
    ): TokenPair {
        return authService.refreshToken(
            token = body.refreshToken
        )
    }

}