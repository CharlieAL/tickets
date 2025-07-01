package com.charlie.tickets.infrastructure.web.response

import com.charlie.tickets.domain.models.Event
import com.charlie.tickets.domain.types.EventStatusEnum
import java.time.LocalDateTime
import java.util.UUID

data class CreateEventResponse(
    val id: UUID,
    val name: String,
    val eventStart: LocalDateTime,
    val eventEnd: LocalDateTime,
    val venue: String,
    val salesStart: LocalDateTime,
    val salesEnd: LocalDateTime,
    val status: EventStatusEnum,
    val ticketTypes: List<CreateTicketTypeResponse>,
    val createdAt: LocalDateTime,
) {
    companion object {
        fun from(event: Event): CreateEventResponse {
            return CreateEventResponse(
                id = requireNotNull(event.id) { "Event ID cannot be null" },
                name = event.name,
                eventStart = event.eventStart,
                eventEnd = event.eventEnd,
                venue = event.venue,
                salesStart = event.salesStart,
                salesEnd = event.salesEnd,
                status = event.status,
                ticketTypes = event.ticketTypes.map { CreateTicketTypeResponse.from(it) },
                createdAt = event.createdAt
            )
        }
    }
}
