package com.charlie.tickets.domain.exceptions

class EventNotFoundException(
    message: String? = "Event not found",
) : RuntimeException(
    message
)