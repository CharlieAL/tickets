package com.charlie.tickets.infrastructure.persistence.postgres.mapper

import com.charlie.tickets.domain.models.User
import com.charlie.tickets.infrastructure.persistence.postgres.entity.UserEntity

fun UserEntity.toDomain(): User {
    return User(
        id = requireNotNull(this.id),
        name = this.name,
        email = this.email,
        password = this.password,
        organizedEvents = emptyList(),
        attendingEvents = emptyList(),
        staffingEvents = emptyList(),
        createdAt = this.createdAt,
        updatedAt = this.updatedAt
    )
}

fun User.toEntity(): UserEntity {
    return UserEntity(
        id = this.id,
        name = this.name,
        email = this.email,
        password = this.password,
        organizedEvents = emptyList(),
        attendingEvents = emptyList(),
        staffingEvents = emptyList(),
        createdAt = this.createdAt,
        updatedAt = this.updatedAt
    )
}