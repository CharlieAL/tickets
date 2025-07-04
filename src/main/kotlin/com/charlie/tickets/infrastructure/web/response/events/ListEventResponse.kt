package com.charlie.tickets.infrastructure.web.response.events

import com.charlie.tickets.domain.models.Event
import com.charlie.tickets.domain.types.EventStatusEnum
import com.charlie.tickets.infrastructure.web.response.TicketTypesResponse
import java.time.LocalDateTime
import java.util.UUID

data class ListEventResponse(
    val id: UUID,
    val name: String,
    val eventStart: LocalDateTime,
    val eventEnd: LocalDateTime,
    val venue: String,
    val salesStart: LocalDateTime,
    val salesEnd: LocalDateTime,
    val status: EventStatusEnum,
    val ticketTypes: List<TicketTypesResponse> = emptyList()
) {
    companion object {
        fun from(event: Event): ListEventResponse {
            return ListEventResponse(
                id = requireNotNull(event.id, { "Event (listEventResponse) ID cannot be null" }),
                name = event.name,
                eventStart = event.eventStart,
                eventEnd = event.eventEnd,
                venue = event.venue,
                salesStart = event.salesStart,
                salesEnd = event.salesEnd,
                status = event.status,
                ticketTypes = event.ticketTypes.map { TicketTypesResponse.Companion.from(it) }
            )
        }
    }
}