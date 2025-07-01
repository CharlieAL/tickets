package com.charlie.tickets.domain.models

import com.charlie.tickets.domain.types.TicketStatusEnum
import java.time.LocalDateTime
import java.util.UUID

data class Ticket(
    val id: UUID,
    val status: TicketStatusEnum,
    val ticketType: TicketType,
    val purchaser: User,
    val validations: List<TicketValidation> = emptyList(),
    val qrCodes: List<QrCode> = emptyList(),
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime
)
