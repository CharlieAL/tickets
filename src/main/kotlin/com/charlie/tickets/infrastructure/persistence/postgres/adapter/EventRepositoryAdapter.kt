package com.charlie.tickets.infrastructure.persistence.postgres.adapter

import com.charlie.tickets.domain.models.Event
import com.charlie.tickets.domain.ports.outgoing.EventRepository
import com.charlie.tickets.infrastructure.persistence.postgres.mapper.toDomain
import com.charlie.tickets.infrastructure.persistence.postgres.mapper.toEntity
import com.charlie.tickets.infrastructure.persistence.postgres.repository.JpaEventRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Component
import java.util.UUID


@Component
class EventRepositoryAdapter(
    private val jpaEvent: JpaEventRepository
) : EventRepository {
    override fun save(event: Event): Event {
        val eventEntity = event.toEntity() // Convert Event domain model to EventEntity
        return jpaEvent.save(eventEntity).toDomain()
    }

    override fun findByOrganizerId(
        organizerId: UUID,
        pageable: Pageable
    ): Page<Event> {
        return jpaEvent.findByOrganizerId(organizerId, pageable)
            .map { it.toDomain() } // Convert each EventEntity to Event domain model
    }

    override fun findByIdAndOrganizerId(
        eventId: UUID,
        organizerId: UUID
    ): Event? {
        return jpaEvent.findByIdAndOrganizerId(eventId, organizerId)
            ?.toDomain() // Convert EventEntity to Event domain model if found
    }

    override fun update(event: Event): Event {
        val eventEntity = event.toEntity() // Convert Event domain model to EventEntity
        return jpaEvent.save(eventEntity).toDomain() // Save and convert back to Event domain model
    }
}