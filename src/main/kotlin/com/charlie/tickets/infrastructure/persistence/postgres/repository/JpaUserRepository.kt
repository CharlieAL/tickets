package com.charlie.tickets.infrastructure.persistence.postgres.repository

import com.charlie.tickets.infrastructure.persistence.postgres.entity.UserEntity
import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID

interface JpaUserRepository : JpaRepository<UserEntity, UUID> {
    fun findByEmail(email: String): UserEntity?
}