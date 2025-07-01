package com.charlie.tickets.domain.ports.outgoing

import com.charlie.tickets.domain.models.Event

interface EventRepository {
    //    fun findById(id: String): Event?
    fun save(event: Event): Event
    fun deleteById(id: String)
//    fun findAll(): List<Event>
//    fun findByOrganizerId(organizerId: String): List<Event>
//    fun findByStatus(status: EventStatusEnum): List<Event>
}