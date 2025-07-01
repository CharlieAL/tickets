package com.charlie.tickets.infrastructure.web.response

import com.charlie.tickets.domain.models.TicketType
import java.time.LocalDateTime
import java.util.UUID

data class CreateTicketTypeResponse(
    val id: UUID,
    val name: String,
    val price: Double,
    val description: String,
    val totalAvailable: Int,
    val createdAt: LocalDateTime,
) {
    companion object {
        fun from(ticketType: TicketType): CreateTicketTypeResponse {
            return CreateTicketTypeResponse(
                id = requireNotNull(ticketType.id) { "TicketType ID cannot be null" },
                name = ticketType.name,
                price = ticketType.price,
                description = ticketType.description,
                totalAvailable = ticketType.totalAvailable,
                createdAt = ticketType.createdAt
            )
        }
    }
}
