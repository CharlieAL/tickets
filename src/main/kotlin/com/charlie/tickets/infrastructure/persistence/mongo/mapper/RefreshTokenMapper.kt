package com.charlie.tickets.infrastructure.persistence.mongo.mapper

import com.charlie.tickets.domain.models.RefreshToken
import com.charlie.tickets.infrastructure.persistence.mongo.document.RefreshTokenDocument


fun RefreshTokenDocument.toDomainModel(): RefreshToken {
    return RefreshToken(
        userId = userId,
        expiresAt = expiresAt,
        hashedToken = hashedToken,
        createdAt = createdAt
    )
}

fun RefreshToken.toDocument(): RefreshTokenDocument {
    return RefreshTokenDocument(
        userId = userId,
        expiresAt = expiresAt,
        hashedToken = hashedToken,
        createdAt = createdAt
    )
}