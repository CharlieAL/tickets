package com.charlie.tickets.domain.ports.incoming

import com.charlie.tickets.domain.models.Event
import com.charlie.tickets.domain.models.command.CreateEventCommand
import java.util.UUID

interface EventUseCase {
    fun create(organizerId: UUID, eventCommand: CreateEventCommand): Event
}