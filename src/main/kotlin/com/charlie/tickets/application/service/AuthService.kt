package com.charlie.tickets.application.service

import com.charlie.tickets.domain.models.RefreshToken
import com.charlie.tickets.domain.models.TokenPair
import com.charlie.tickets.domain.models.User
import com.charlie.tickets.domain.ports.incoming.AuthUseCase
import com.charlie.tickets.domain.ports.outgoing.RefreshTokenRepository
import com.charlie.tickets.domain.ports.outgoing.UserRepository
import com.charlie.tickets.infrastructure.security.BcryHash
import com.charlie.tickets.infrastructure.security.jwt.JwtServiceImpl
import org.springframework.http.HttpStatusCode
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.server.ResponseStatusException
import java.security.MessageDigest
import java.time.Instant
import java.util.Base64
import java.util.UUID

@Service
class AuthService(
    private val jwtServiceImpl: JwtServiceImpl,
    private val userRepository: UserRepository,
    private val hashEncoder: BcryHash,
    private val refreshTokenRepository: RefreshTokenRepository
) : AuthUseCase {
    override fun login(email: String, password: String): TokenPair {
        val user = userRepository.findByEmail(email) ?: throw BadCredentialsException("Invalid credentials")

        if (!hashEncoder.matches(password, user.password)) {
            throw BadCredentialsException("Invalid credentials")
        }

        if (user.id == null) {
            throw ResponseStatusException(HttpStatusCode.valueOf(500), "User ID is null")
        }


        val newAccessToken = jwtServiceImpl.generateAccessToken(user.id)
        val newRefreshToken = jwtServiceImpl.generateRefreshToken(user.id)

//        delete any existing refresh tokens for the user
        refreshTokenRepository.deleteByUserId(user.id)


        storeRefreshToken(user.id, newRefreshToken)

        return TokenPair(
            accessToken = newAccessToken,
            refreshToken = newRefreshToken
        )
    }

    override fun logout(token: String): Boolean {
        val user = getUserFromToken(token)

        if (user.id == null) {
            throw ResponseStatusException(HttpStatusCode.valueOf(500), "User ID is null")
        }

        val hashed = hashToken(token)
        refreshTokenRepository.findByUserIdAndHashedToken(user.id, hashed)
            ?: throw ResponseStatusException(HttpStatusCode.valueOf(401), "Refresh token not recognized")

        refreshTokenRepository.deleteByUserIdAndHashedToken(user.id, hashed)

        return true
    }

    override fun register(name: String, email: String, password: String): User {
        val user = userRepository.findByEmail(email.trim())
        if (user != null) {
            throw ResponseStatusException(HttpStatusCode.valueOf(409), "User with this email already exists")
        }

        return userRepository.save(
            User(
                email = email,
                password = hashEncoder.encode(password),
                name = name,
            )
        )

    }

    @Transactional
    override fun refreshToken(token: String): TokenPair {
        val user = getUserFromToken(token)

        if (user.id == null) {
            throw ResponseStatusException(HttpStatusCode.valueOf(500), "User ID is null")
        }

        val hashed = hashToken(token)
        refreshTokenRepository.findByUserIdAndHashedToken(user.id, hashed)
            ?: throw ResponseStatusException(HttpStatusCode.valueOf(401), "Refresh token not recognized")

        refreshTokenRepository.deleteByUserIdAndHashedToken(user.id, hashed)

        val newAccessToken = jwtServiceImpl.generateAccessToken(user.id)
        val newRefreshToken = jwtServiceImpl.generateRefreshToken(user.id)

        storeRefreshToken(user.id, newRefreshToken)

        return TokenPair(
            accessToken = newAccessToken,
            refreshToken = newRefreshToken
        )
    }

    private fun storeRefreshToken(userId: UUID, rawRefreshToken: String) {
        val hashed = hashToken(rawRefreshToken)
        val expiryMs = jwtServiceImpl.refreshTokenValidityMs
        val expiresAt = Instant.now().plusMillis(expiryMs)
        refreshTokenRepository.save(
            RefreshToken(
                userId = userId,
                expiresAt = expiresAt,
                hashedToken = hashed
            )
        )

    }

    private fun hashToken(token: String): String {
        val digest = MessageDigest.getInstance("SHA-256")
        val hashedBytes = digest.digest(token.encodeToByteArray())
        return Base64.getEncoder().encodeToString(hashedBytes)
    }

    private fun getUserFromToken(token: String): User {
        if (!jwtServiceImpl.validateRefreshToken(token)) {
            throw ResponseStatusException(HttpStatusCode.valueOf(401), "Invalid refresh token")
        }
        val userId = jwtServiceImpl.getUserIdFromToken(token)

        return userRepository.findById(userId)
            ?: throw ResponseStatusException(HttpStatusCode.valueOf(401), "User not found")
    }
}