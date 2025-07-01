package com.charlie.tickets.domain.ports.outgoing

import com.charlie.tickets.domain.models.User
import java.util.UUID

interface UserRepository {
    fun findByEmail(email: String): User?
    fun save(user: User): User
    fun findById(id: UUID): User?
}