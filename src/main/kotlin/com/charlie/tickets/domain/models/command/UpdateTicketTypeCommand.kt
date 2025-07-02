package com.charlie.tickets.domain.models.command

import java.util.UUID

data class UpdateTicketTypeCommand(
    val id: UUID? = null,
    val name: String,
    val price: Double,
    val description: String,
    val totalAvailable: Int
)
