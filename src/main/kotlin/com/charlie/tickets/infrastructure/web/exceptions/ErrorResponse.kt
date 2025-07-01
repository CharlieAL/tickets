package com.charlie.tickets.infrastructure.web.exceptions

data class ErrorResponse(
    val code: String,
    val message: String
)
