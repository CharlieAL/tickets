package com.charlie.tickets.infrastructure.persistence.mongo.adapter

import com.charlie.tickets.domain.models.RefreshToken
import com.charlie.tickets.domain.ports.outgoing.RefreshTokenRepository
import com.charlie.tickets.infrastructure.persistence.mongo.mapper.toDocument
import com.charlie.tickets.infrastructure.persistence.mongo.mapper.toDomainModel
import com.charlie.tickets.infrastructure.persistence.mongo.repository.MongoRefreshTokenRepository
import org.bson.types.ObjectId
import org.springframework.stereotype.Component
import java.util.UUID


@Component
class RefreshTokenRepositoryAdapter(
    private val mongoRefreshTokenRepository: MongoRefreshTokenRepository
) : RefreshTokenRepository {
    override fun findByUserIdAndHashedToken(
        userId: UUID,
        hashedToken: String
    ): RefreshToken? {
        val refreshToken = mongoRefreshTokenRepository.findByUserIdAndHashedToken(userId, hashedToken)
            ?: return null // Return null if no token is found
        return refreshToken.toDomainModel() // Assuming RefreshToken is already in the correct format

    }

    override fun deleteByUserIdAndHashedToken(userId: UUID, hashedToken: String) {
        mongoRefreshTokenRepository.deleteByUserIdAndHashedToken(userId, hashedToken)
    }


    override fun save(refreshToken: RefreshToken): RefreshToken {
        val saved = mongoRefreshTokenRepository.save(refreshToken.toDocument())
        return saved.toDomainModel() // Convert the saved entity back to the domain model
    }

}