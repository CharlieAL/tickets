package com.charlie.tickets.domain.exceptions

class TicketTypesNotFoundException(
    message: String? = "Ticket type not found",
) : RuntimeException(
    message
)