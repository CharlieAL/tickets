package com.charlie.tickets.infrastructure.web.controllers

import com.charlie.tickets.application.service.EventService
import com.charlie.tickets.infrastructure.web.request.CreateEventRequest
import com.charlie.tickets.infrastructure.web.response.CreateEventResponse
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.bind.annotation.PostMapping
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

    @PostMapping()
    fun create(
        @RequestBody request: CreateEventRequest
    ): ResponseEntity<CreateEventResponse> {
        val userId = currentUserId()
        val eventCommand = request.toCommand()
        val event = eventService.create(organizerId = userId, eventCommand = eventCommand)
        val response = CreateEventResponse.from(event)
        return ResponseEntity.status(HttpStatus.CREATED).body(response)
    }
}