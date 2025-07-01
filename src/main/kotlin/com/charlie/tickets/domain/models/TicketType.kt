package com.charlie.tickets.domain.models

import java.time.LocalDateTime
import java.util.UUID

data class TicketType(
    val id: UUID? = null,
    val name: String,
    val price: Double,
    val description: String,
    val totalAvailable: Int,
    val event: Event? = null,
    val tickets: List<Ticket> = emptyList(),
    val createdAt: LocalDateTime = LocalDateTime.now(),
    val updatedAt: LocalDateTime = LocalDateTime.now()
)
