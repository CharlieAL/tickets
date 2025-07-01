package com.charlie.tickets.infrastructure.persistence.mongo.repository

import com.charlie.tickets.infrastructure.persistence.mongo.document.RefreshTokenDocument
import org.bson.types.ObjectId
import org.springframework.data.mongodb.repository.MongoRepository
import java.util.UUID

interface MongoRefreshTokenRepository : MongoRepository<RefreshTokenDocument, ObjectId> {
    fun findByUserIdAndHashedToken(userId: UUID, hashedToken: String): RefreshTokenDocument?

    fun deleteByUserIdAndHashedToken(userId: UUID, hashedToken: String)
}