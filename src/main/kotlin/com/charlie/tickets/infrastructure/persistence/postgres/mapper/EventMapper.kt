package com.charlie.tickets.infrastructure.persistence.postgres.mapper

import com.charlie.tickets.domain.models.Event
import com.charlie.tickets.infrastructure.persistence.postgres.entity.EventEntity

fun EventEntity.toDomain(): Event {
    return Event(
        id = this.id,
        name = this.name,
        eventStart = this.eventStart,
        eventEnd = this.eventEnd,
        venue = this.venue,
        salesStart = this.salesStart,
        salesEnd = this.salesEnd,
        status = this.status,
        organizer = this.organizer.toDomain(),
        attendees = emptyList(),
        staff = emptyList(),
        ticketTypes = this.ticketTypeEntities.map { it.toDomain() }.toMutableList(),
        createdAt = this.createdAt,
        updatedAt = this.updatedAt
    )
}

fun Event.toEntity(): EventEntity {
    val eventEntity = EventEntity(
        id = this.id,
        name = this.name,
        eventStart = this.eventStart,
        eventEnd = this.eventEnd,
        venue = this.venue,
        salesStart = this.salesStart,
        salesEnd = this.salesEnd,
        status = this.status,
        organizer = this.organizer.toEntity(),
        attendees = emptyList(),
        staff = emptyList(),
        createdAt = this.createdAt,
        updatedAt = this.updatedAt,
        ticketTypeEntities = mutableListOf()
    )

    val ticketTypes = this.ticketTypes.map {
        it.toEntity(eventEntity)
    }.toMutableList()

    eventEntity.ticketTypeEntities = ticketTypes

    return eventEntity
}
