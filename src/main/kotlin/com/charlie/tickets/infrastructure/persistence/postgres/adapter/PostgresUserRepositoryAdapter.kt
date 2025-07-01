package com.charlie.tickets.infrastructure.persistence.postgres.adapter

import com.charlie.tickets.domain.models.User
import com.charlie.tickets.domain.ports.outgoing.UserRepository
import com.charlie.tickets.infrastructure.persistence.postgres.mapper.toDomain
import com.charlie.tickets.infrastructure.persistence.postgres.mapper.toEntity
import com.charlie.tickets.infrastructure.persistence.postgres.repository.JpaUserRepository
import org.springframework.stereotype.Component
import java.util.UUID

@Component
class PostgresUserRepositoryAdapter(
    private val jpaRepo: JpaUserRepository
) : UserRepository {
    override fun findByEmail(email: String): User? {
        val userEntity = jpaRepo.findByEmail(email)
        return userEntity?.toDomain() // Convert UserEntity to User domain model
    }

    override fun save(user: User): User {
        val userEntity = user.toEntity() // Convert User domain model to UserEntity
        val savedEntity = jpaRepo.save(userEntity)
        return savedEntity.toDomain() // Convert saved UserEntity back to User domain model
    }

    override fun findById(id: UUID): User? {
        val userEntity = jpaRepo.findById(id).orElse(null)
        return userEntity?.toDomain() // Convert UserEntity to User domain model
    }
}