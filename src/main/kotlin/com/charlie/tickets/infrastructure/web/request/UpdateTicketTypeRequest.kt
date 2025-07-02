package com.charlie.tickets.infrastructure.web.request

import com.charlie.tickets.domain.models.command.UpdateTicketTypeCommand
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.PositiveOrZero
import java.util.UUID

data class UpdateTicketTypeRequest(
    val id: UUID? = null,
    @NotBlank(message = "Ticket type name is required")
    val name: String,
    @NotNull(message = "Price is required")
    @PositiveOrZero(message = "Price must be zero or greater")
    val price: Double,
    val description: String,
    val totalAvailable: Int
) {
    fun toCommand() = UpdateTicketTypeCommand(
        id = id,
        name = name,
        price = price,
        description = description,
        totalAvailable = totalAvailable
    )
}
