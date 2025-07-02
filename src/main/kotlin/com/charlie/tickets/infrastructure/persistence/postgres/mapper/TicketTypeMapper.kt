package com.charlie.tickets.infrastructure.persistence.postgres.mapper

import com.charlie.tickets.domain.models.TicketType
import com.charlie.tickets.infrastructure.persistence.postgres.entity.EventEntity
import com.charlie.tickets.infrastructure.persistence.postgres.entity.TicketTypeEntity

// mappers
fun TicketTypeEntity.toDomain(): TicketType {
    return TicketType(
        id = this.id,
        name = this.name,
        price = this.price,
        description = this.description,
        totalAvailable = this.totalAvailable,
    )
}

fun TicketType.toEntity(eventEntity: EventEntity): TicketTypeEntity {
    return TicketTypeEntity(
        id = this.id,
        name = this.name,
        price = this.price,
        description = this.description,
        totalAvailable = this.totalAvailable,
        event = eventEntity,
        createdAt = this.createdAt,
        updatedAt = this.updatedAt,
        ticketEntities = mutableListOf() // opcional
    )
}
