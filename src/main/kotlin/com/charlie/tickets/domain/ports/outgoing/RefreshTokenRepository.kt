package com.charlie.tickets.domain.ports.outgoing

import com.charlie.tickets.domain.models.RefreshToken
import java.util.UUID

interface RefreshTokenRepository {

    fun findByUserIdAndHashedToken(userId: UUID, hashedToken: String): RefreshToken?

    fun deleteByUserIdAndHashedToken(userId: UUID, hashedToken: String)

    fun save(refreshToken: RefreshToken): RefreshToken
}