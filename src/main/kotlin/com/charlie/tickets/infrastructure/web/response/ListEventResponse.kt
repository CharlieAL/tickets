package com.charlie.tickets.infrastructure.web.response

import com.charlie.tickets.domain.models.Event
import com.charlie.tickets.domain.types.EventStatusEnum
import java.time.LocalDateTime

data class ListEventResponse(
    val name: String,
    val eventStart: LocalDateTime,
    val eventEnd: LocalDateTime,
    val venue: String,
    val salesStart: LocalDateTime,
    val salesEnd: LocalDateTime,
    val status: EventStatusEnum,
    val ticketTypes: List<ListEventTicketTypeResponse> = emptyList()
) {
    companion object {
        fun from(event: Event): ListEventResponse {
            return ListEventResponse(
                name = event.name,
                eventStart = event.eventStart,
                eventEnd = event.eventEnd,
                venue = event.venue,
                salesStart = event.salesStart,
                salesEnd = event.salesEnd,
                status = event.status,
                ticketTypes = event.ticketTypes.map { ListEventTicketTypeResponse.from(it) }
            )
        }
    }
}