package com.charlie.tickets.infrastructure.web.response

import com.charlie.tickets.domain.models.TicketType
import java.util.UUID

data class ListEventTicketTypeResponse(
    val id: UUID,
    val name: String,
    val price: Double,
    val description: String,
    val totalAvailable: Int,
) {
    companion object {
        fun from(ticketType: TicketType): ListEventTicketTypeResponse {
            return ListEventTicketTypeResponse(
                id = requireNotNull(ticketType.id),
                name = ticketType.name,
                price = ticketType.price,
                description = ticketType.description,
                totalAvailable = ticketType.totalAvailable
            )
        }
    }
}
