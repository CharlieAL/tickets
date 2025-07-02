package com.charlie.tickets.domain.models.command

import com.charlie.tickets.domain.types.EventStatusEnum
import java.time.LocalDateTime
import java.util.UUID

data class UpdateEventCommand(
    val id: UUID? = null,
    val name: String,
    val eventStart: LocalDateTime,
    val eventEnd: LocalDateTime,
    val venue: String,
    val salesStart: LocalDateTime,
    val salesEnd: LocalDateTime,
    val status: EventStatusEnum,
    val ticketTypes: List<UpdateTicketTypeCommand> = emptyList()
)
