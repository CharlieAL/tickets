package com.charlie.tickets.domain.models

import java.time.Instant
import java.util.UUID

data class RefreshToken(
    val userId: UUID,
    val expiresAt: Instant,
    val hashedToken: String,
    val createdAt: Instant = Instant.now()
)