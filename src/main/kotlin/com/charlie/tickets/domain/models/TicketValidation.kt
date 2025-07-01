package com.charlie.tickets.domain.models

import com.charlie.tickets.domain.types.TicketValidationMethodEnum
import com.charlie.tickets.domain.types.TicketValidationStatusEnum
import java.time.LocalDateTime

data class TicketValidation(
    val id: String,
    val status: TicketValidationStatusEnum,
    val validationMethod: TicketValidationMethodEnum,
    val ticket: Ticket,
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime
)
