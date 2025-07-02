package com.charlie.tickets.infrastructure.web.controllers

import com.charlie.tickets.application.service.EventService
import com.charlie.tickets.infrastructure.web.request.CreateEventRequest
import com.charlie.tickets.infrastructure.web.request.UpdateEventRequest
import com.charlie.tickets.infrastructure.web.response.events.CreateEventResponse
import com.charlie.tickets.infrastructure.web.response.events.GetEventDetailsResponse
import com.charlie.tickets.infrastructure.web.response.events.ListEventResponse
import com.charlie.tickets.infrastructure.web.response.events.UpdateEventResponse
import jakarta.validation.Valid
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.mongodb.repository.Update
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.UUID

@RestController
@RequestMapping("/api/v1/events")
class EventController(
    private val eventService: EventService
) {

    private fun currentUserId(): UUID {
        val auth = SecurityContextHolder.getContext().authentication
        return auth.principal as UUID
    }

    @PostMapping
    fun create(
        @Valid @RequestBody request: CreateEventRequest
    ): ResponseEntity<CreateEventResponse> {
        val userId = currentUserId()
        val eventCommand = request.toCommand()
        val event = eventService.create(organizerId = userId, eventCommand = eventCommand)
        val response = CreateEventResponse.from(event)
        return ResponseEntity.status(HttpStatus.CREATED).body(response)
    }

    @PutMapping("/{eventId}")
    fun update(
        @PathVariable eventId: UUID,
        @Valid @RequestBody request: UpdateEventRequest
    ): ResponseEntity<UpdateEventResponse> {
        val userId = currentUserId()
        val eventCommand = request.toCommand()
        val updateEvent = eventService.updateEventForOrganizer(userId, id = eventId, eventCommand)
        val response = UpdateEventResponse.from(updateEvent)
        return ResponseEntity.ok(response)
    }

    @GetMapping
    fun listEvents(
        pageable: Pageable
    ): ResponseEntity<Page<ListEventResponse>> {
        val userId = currentUserId()
        val eventsPage = eventService.listEventsForOrganizer(organizerId = userId, pageable = pageable)
        val responsePage = eventsPage.map { ListEventResponse.from(it) }
        return ResponseEntity.ok(responsePage)
    }

    @GetMapping("/{eventId}")
    fun getEventDetails(
        @PathVariable eventId: UUID
    ): ResponseEntity<GetEventDetailsResponse> {
        val userId = currentUserId()
        val event = eventService.getEventForOrganizer(organizerId = userId, eventId = eventId)
        val response = GetEventDetailsResponse.from(event)
        return ResponseEntity.ok(response)
    }
}