package com.charlie.tickets.infrastructure.security.jwt

import com.charlie.tickets.domain.ports.outgoing.JwtService
import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.security.Keys
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpStatusCode
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException
import java.util.Base64
import java.util.Date
import java.util.UUID

@Service
class JwtServiceImpl(
    @Value("\${jwt.secret}") private val jwtSecret: String
) : JwtService {
    private val secretkey = Keys.hmacShaKeyFor(Base64.getDecoder().decode(jwtSecret))
    private val accessTokenValidityMs = 15L * 60L * 1000L // 15 minutes
    val refreshTokenValidityMs = 7L * 24L * 60L * 60L * 1000L // 7 days

    private fun generateToken(
        userId: String,
        type: String,
        expiry: Long
    ): String {
        val now = Date()
        val expiryDate = Date(now.time + expiry)
        return Jwts.builder()
            .subject(userId)
            .claim("type", type)
            .issuedAt(now)
            .expiration(expiryDate)
            .signWith(secretkey, Jwts.SIG.HS256)
            .compact()
    }

    override fun generateAccessToken(userId: UUID): String {
        val id = userId.toString()
        return generateToken(id, "access", accessTokenValidityMs)
    }

    override fun generateRefreshToken(userId: UUID): String {
        val id = userId.toString()
        return generateToken(id, "refresh", refreshTokenValidityMs)
    }

    override fun validateAccessToken(token: String): Boolean {
        val claims = parseAllClaims(token) ?: return false
        val tokenType = claims["type"] as? String ?: return false
        return tokenType == "access"
    }

    override fun validateRefreshToken(token: String): Boolean {
        val claims = parseAllClaims(token) ?: return false
        val tokenType = claims["type"] as? String ?: return false
        return tokenType == "refresh"
    }

    override fun getUserIdFromToken(token: String): UUID {
        val claims =
            parseAllClaims(token) ?: throw ResponseStatusException(HttpStatusCode.valueOf(401), "Invalid token")
        return UUID.fromString(claims.subject)
    }

    private fun parseAllClaims(token: String): Claims? {
        val rawToken = if (token.startsWith("Bearer ")) {
            token.removePrefix("Bearer ")
        } else token
        return try {
            Jwts.parser()
                .verifyWith(secretkey)
                .build()
                .parseSignedClaims(rawToken)
                .payload
        } catch (e: Exception) {
            null
        }
    }

}