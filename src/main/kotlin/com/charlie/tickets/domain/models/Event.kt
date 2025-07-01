package com.charlie.tickets.domain.models

import com.charlie.tickets.domain.types.EventStatusEnum
import java.time.LocalDateTime
import java.util.UUID


data class Event(
    val id: UUID? = null,
    val name: String,
    val eventStart: LocalDateTime,
    val eventEnd: LocalDateTime,
    val venue: String,
    val salesStart: LocalDateTime,
    val salesEnd: LocalDateTime,
    val status: EventStatusEnum,
    val organizer: User,
    val attendees: List<User> = emptyList(),
    val staff: List<User> = emptyList(),
    val ticketTypes: List<TicketType> = emptyList(),
    val createdAt: LocalDateTime = LocalDateTime.now(),
    val updatedAt: LocalDateTime = LocalDateTime.now()
)