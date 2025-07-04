package com.charlie.tickets.application.service

import com.charlie.tickets.domain.exceptions.EventNotFoundException
import com.charlie.tickets.domain.exceptions.EventUpdateException
import com.charlie.tickets.domain.exceptions.UserNotFoundException
import com.charlie.tickets.domain.models.Event
import com.charlie.tickets.domain.models.TicketType
import com.charlie.tickets.domain.models.command.CreateEventCommand
import com.charlie.tickets.domain.models.command.UpdateEventCommand
import com.charlie.tickets.domain.ports.incoming.EventUseCase
import com.charlie.tickets.domain.ports.outgoing.EventRepository
import com.charlie.tickets.domain.ports.outgoing.UserRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime
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

    override fun listEventsForOrganizer(organizerId: UUID, pageable: Pageable): Page<Event> {
        return eventRepository.findByOrganizerId(organizerId, pageable)
    }

    override fun getEventForOrganizer(organizerId: UUID, eventId: UUID): Event {
        return eventRepository.findByIdAndOrganizerId(eventId, organizerId)
            ?: throw EventNotFoundException("Event with ID $eventId not found for organizer with ID $organizerId")
    }

    @Transactional
    override fun updateEventForOrganizer(organizerId: UUID, id: UUID, eventCommand: UpdateEventCommand): Event {
        if (eventCommand.id == null) {
            throw EventUpdateException("Event ID must be provided for update")
        }
        if (id != eventCommand.id) {
            throw EventUpdateException("Event ID in command does not match the provided ID")
        }
        val existingEvent = eventRepository.findByIdAndOrganizerId(id, organizerId)
            ?: throw EventNotFoundException("Event with ID $id not found for organizer with ID $organizerId")


        val updateTicketTypes = eventCommand.ticketTypes.map {
            val ticketType = it.id?.let { existingId ->
                existingEvent.ticketTypes.find { ticket -> ticket.id == existingId }?.copy(
                    name = it.name,
                    price = it.price,
                    totalAvailable = it.totalAvailable,
                    description = it.description
                ) ?: throw EventUpdateException("Ticket type with ID ${it.id} not found in event")
            } ?: TicketType(
                name = it.name,
                price = it.price,
                totalAvailable = it.totalAvailable,
                description = it.description,
                event = existingEvent
            )

            ticketType
        }.toMutableList()


        val updatedEvent = existingEvent.copy(
            name = eventCommand.name,
            eventStart = eventCommand.eventStart,
            eventEnd = eventCommand.eventEnd,
            venue = eventCommand.venue,
            salesStart = eventCommand.salesStart,
            salesEnd = eventCommand.salesEnd,
            status = eventCommand.status,
            ticketTypes = updateTicketTypes,
            updatedAt = LocalDateTime.now()
        )
        return eventRepository.update(updatedEvent)
    }

    @Transactional
    override fun deleteEventForOrganizer(organizerId: UUID, eventId: UUID) {
        getEventForOrganizer(
            organizerId = organizerId,
            eventId = eventId
        ).let { eventRepository.delete(it) }
    }
}