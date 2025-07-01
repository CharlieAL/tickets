package com.charlie.tickets.application.service

import com.charlie.tickets.domain.exceptions.UserNotFoundException
import com.charlie.tickets.domain.models.Event
import com.charlie.tickets.domain.models.TicketType
import com.charlie.tickets.domain.models.command.CreateEventCommand
import com.charlie.tickets.domain.ports.incoming.EventUseCase
import com.charlie.tickets.domain.ports.outgoing.EventRepository
import com.charlie.tickets.domain.ports.outgoing.UserRepository
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class EventService(
    private val eventRepository: EventRepository,
    private val userRepository: UserRepository
) : EventUseCase {
    override fun create(organizerId: UUID, eventCommand: CreateEventCommand): Event {
        val user = userRepository.findById(organizerId)
            ?: throw UserNotFoundException(organizerId)


        val event = Event(
            name = eventCommand.name,
            eventStart = eventCommand.eventStart,
            eventEnd = eventCommand.eventEnd,
            venue = eventCommand.venue,
            salesStart = eventCommand.salesStart,
            salesEnd = eventCommand.salesEnd,
            status = eventCommand.status,
            organizer = user,
        )

        val ticketTypes = eventCommand.ticketTypes.map {
            TicketType(
                name = it.name,
                price = it.price,
                totalAvailable = it.totalAvailable,
                description = it.description,
                event = event
            )
        }.toMutableList()

        event.ticketTypes = ticketTypes


        return eventRepository.save(event)
    }
}