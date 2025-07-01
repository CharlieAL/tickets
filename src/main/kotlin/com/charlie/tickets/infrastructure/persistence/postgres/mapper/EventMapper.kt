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
        createdAt = this.createdAt,
        updatedAt = this.updatedAt,
        organizer = this.organizer.toDomain(),
        attendees = this.attendees.map { it.toDomain() },
        staff = this.staff.map { it.toDomain() },
        ticketTypes = this.ticketTypeEntities.map { it.toDomain() }
    )
}

fun Event.toEntity(): EventEntity {
    return EventEntity(
        name = this.name,
        eventStart = this.eventStart,
        eventEnd = this.eventEnd,
        venue = this.venue,
        salesStart = this.salesStart,
        salesEnd = this.salesEnd,
        status = this.status,
        createdAt = this.createdAt,
        updatedAt = this.updatedAt,
        organizer = this.organizer.toEntity(),
        attendees = this.attendees.map { it.toEntity() },
        staff = this.staff.map { it.toEntity() },
        ticketTypeEntities = this.ticketTypes.map { it.toEntity() }
    )
}