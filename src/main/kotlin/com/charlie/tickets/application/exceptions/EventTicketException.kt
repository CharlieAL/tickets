package com.charlie.tickets.application.exceptions

open class EventTicketException(
    message: String? = null,
    cause: Throwable? = null,
    enableSuppression: Boolean = true,
    writableStackTrace: Boolean = true
) : RuntimeException(
    message ?: "An error occurred with the event ticket",
    cause,
    enableSuppression,
    writableStackTrace
)