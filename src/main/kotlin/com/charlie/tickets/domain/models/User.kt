package com.charlie.tickets.domain.models

import java.time.LocalDateTime
import java.util.UUID

data class User(
    val id: UUID? = null,
    val name: String,
    val email: String,
    val password: String,
    val organizedEvents: List<Event> = emptyList(),
    val attendingEvents: List<Event> = emptyList(),
    val staffingEvents: List<Event> = emptyList(),
    val createdAt: LocalDateTime = LocalDateTime.now(),
    val updatedAt: LocalDateTime = LocalDateTime.now()
)