package com.charlie.tickets.infrastructure.persistence.postgres.mapper

import com.charlie.tickets.domain.models.TicketType
import com.charlie.tickets.infrastructure.persistence.postgres.entity.TicketTypeEntity
import java.time.LocalDateTime

// mappers
fun TicketTypeEntity.toDomain(): TicketType {
    return TicketType(
        id = this.id,
        name = this.name,
        price = this.price,
        description = this.description,
        totalAvailable = this.totalAvailable,
        event = this.event?.toDomain(),
    )
}

fun TicketType.toEntity(): TicketTypeEntity {
    return TicketTypeEntity(
        name = this.name,
        price = this.price,
        description = this.description,
        totalAvailable = this.totalAvailable,
        createdAt = LocalDateTime.now(),
        updatedAt = LocalDateTime.now(),
        event = this.event?.toEntity()
    )
}