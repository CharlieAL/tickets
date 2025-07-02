package com.charlie.tickets.domain.ports.outgoing

import com.charlie.tickets.domain.models.Event
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import java.util.UUID

interface EventRepository {
    fun save(event: Event): Event
    fun findByOrganizerId(organizerId: UUID, pageable: Pageable): Page<Event>
    fun findByIdAndOrganizerId(eventId: UUID, organizerId: UUID): Event?
    fun update(event: Event): Event
}