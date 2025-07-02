package com.charlie.tickets.domain.ports.incoming

import com.charlie.tickets.domain.models.Event
import com.charlie.tickets.domain.models.command.CreateEventCommand
import com.charlie.tickets.domain.models.command.UpdateEventCommand
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import java.util.UUID

interface EventUseCase {
    fun create(organizerId: UUID, eventCommand: CreateEventCommand): Event
    fun listEventsForOrganizer(organizerId: UUID, pageable: Pageable): Page<Event>
    fun getEventForOrganizer(organizerId: UUID, eventId: UUID): Event?
    fun updateEventForOrganizer(organizerId: UUID, id: UUID, eventCommand: UpdateEventCommand): Event
}