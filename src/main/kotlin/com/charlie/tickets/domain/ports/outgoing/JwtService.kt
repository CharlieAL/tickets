package com.charlie.tickets.domain.ports.outgoing

import java.util.UUID


interface JwtService {
    fun generateAccessToken(userId: UUID): String
    fun generateRefreshToken(userId: UUID): String
    fun validateAccessToken(token: String): Boolean
    fun validateRefreshToken(token: String): Boolean
    fun getUserIdFromToken(token: String): UUID
}