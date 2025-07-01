package com.charlie.tickets.domain.models.command

data class CreateTicketTypeCommand(
    val name: String,
    val price: Double,
    val description: String,
    val totalAvailable: Int
)
