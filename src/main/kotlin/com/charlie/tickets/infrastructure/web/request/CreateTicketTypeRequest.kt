package com.charlie.tickets.infrastructure.web.request

import com.charlie.tickets.domain.models.command.CreateTicketTypeCommand
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.PositiveOrZero

data class CreateTicketTypeRequest(
    @NotBlank(message = "Ticket type name is required")
    val name: String,
    @NotNull(message = "Price is required")
    @PositiveOrZero(message = "Price must be zero or greater")
    val price: Double,
    val description: String,
    val totalAvailable: Int
) {
    fun toCommand() = CreateTicketTypeCommand(
        name = name,
        price = price,
        description = description,
        totalAvailable = totalAvailable
    )
}
