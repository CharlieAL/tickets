package com.charlie.tickets.domain.models.command

import com.charlie.tickets.domain.models.User
import com.charlie.tickets.domain.types.EventStatusEnum
import java.time.LocalDateTime

data class CreateEventCommand(
    val name: String,
    val eventStart: LocalDateTime,
    val eventEnd: LocalDateTime,
    val venue: String,
    val salesStart: LocalDateTime,
    val salesEnd: LocalDateTime,
    val status: EventStatusEnum,
    val ticketTypes: List<CreateTicketTypeCommand> = emptyList()
)
