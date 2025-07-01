package com.charlie.tickets.infrastructure.persistence.postgres.entity

import com.charlie.tickets.domain.types.QrCodeStatusEnum
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
import java.util.UUID

@Entity
@Table(name = "qr_codes")
data class QrCodeEntity(
    @Id
    @Column(updatable = false, nullable = false)
    @GeneratedValue(strategy = GenerationType.UUID)
    val id: UUID,

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    val status: QrCodeStatusEnum,

    @Column(nullable = false)
    val value: String,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ticket_id")
    val ticketEntity: TicketEntity,

    @CreatedDate
    @Column(name = "created_at", updatable = false, nullable = false)
    val createdAt: LocalDateTime,

    @LastModifiedDate
    @Column(name = "updated_at", nullable = false)
    val updatedAt: LocalDateTime
)