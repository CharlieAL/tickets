package com.charlie.tickets.infrastructure.persistence.postgres.repository

import com.charlie.tickets.infrastructure.persistence.postgres.entity.EventEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
interface JpaEventRepository : JpaRepository<EventEntity, UUID> {
    fun findByOrganizerId(organizerId: UUID): List<EventEntity>
}