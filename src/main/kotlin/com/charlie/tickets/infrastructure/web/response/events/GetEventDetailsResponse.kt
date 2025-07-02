package com.charlie.tickets.infrastructure.web.response.events

import com.charlie.tickets.domain.models.Event
import com.charlie.tickets.infrastructure.web.response.TicketTypesResponse
import java.time.LocalDateTime

data class GetEventDetailsResponse(
    val id: String,
    val name: String,
    val eventStart: LocalDateTime,
    val eventEnd: LocalDateTime,
    val venue: String,
    val salesStart: LocalDateTime,
    val salesEnd: LocalDateTime,
    val status: String,
    val ticketTypes: List<TicketTypesResponse> = emptyList(),
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime
) {
    companion object {
        fun from(event: Event): GetEventDetailsResponse {
            return GetEventDetailsResponse(
                id = event.id.toString(),
                name = event.name,
                eventStart = event.eventStart,
                eventEnd = event.eventEnd,
                venue = event.venue,
                salesStart = event.salesStart,
                salesEnd = event.salesEnd,
                status = event.status.name,
                ticketTypes = event.ticketTypes.map { TicketTypesResponse.from(it) },
                createdAt = event.createdAt,
                updatedAt = event.updatedAt
            )
        }
    }
}
