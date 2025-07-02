package com.charlie.tickets.domain.exceptions

class EventUpdateException(
    message: String? = "Event update failed due to invalid data or state.",
) : RuntimeException(
    message
)