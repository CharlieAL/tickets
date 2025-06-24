package com.charlie.tickets.domain.models

import com.charlie.tickets.domain.TicketValidationMethodEnum
import com.charlie.tickets.domain.TicketValidationStatusEnum
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.FetchType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import java.time.LocalDateTime

@Entity
@Table(name = "ticket_validations")
data class TicketValidation(
    @Id
    @Column(updatable = false, nullable = false)
    @GeneratedValue(strategy = GenerationType.UUID)
    val id: String,

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    val status: TicketValidationStatusEnum,

    @Column(name = "validation_method", nullable = false)
    @Enumerated(EnumType.STRING)
    val validationMethod: TicketValidationMethodEnum,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ticket_id", nullable = false)
    val ticket: Ticket,

    @CreatedDate
    @Column(name = "created_at", updatable = false, nullable = false)
    val createdAt: LocalDateTime,

    @LastModifiedDate
    @Column(name = "updated_at", nullable = false)
    val updatedAt: LocalDateTime
)
