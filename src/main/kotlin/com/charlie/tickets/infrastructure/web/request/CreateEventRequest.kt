package com.charlie.tickets.infrastructure.web.request

import com.charlie.tickets.domain.models.User
import com.charlie.tickets.domain.models.command.CreateEventCommand
import com.charlie.tickets.domain.types.EventStatusEnum
import jakarta.validation.Valid
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.NotNull
import java.time.LocalDateTime

data class CreateEventRequest(
    @NotBlank(message = "Event name is required")
    val name: String,
    val eventStart: LocalDateTime,
    val eventEnd: LocalDateTime,
    @NotBlank(message = "Venue information is required")
    val venue: String,
    val salesStart: LocalDateTime,
    val salesEnd: LocalDateTime,

    @NotNull(message = "Event status must be provided")
    val status: EventStatusEnum,

    @NotEmpty(message = "At least one ticket type is required")
    @Valid
    val ticketTypes: List<CreateTicketTypeRequest> = emptyList()
) {
    fun toCommand() = CreateEventCommand(
        name = name,
        eventStart = eventStart,
        eventEnd = eventEnd,
        venue = venue,
        salesStart = salesStart,
        salesEnd = salesEnd,
        status = status,
        ticketTypes = ticketTypes.map { it.toCommand() }
    )
}