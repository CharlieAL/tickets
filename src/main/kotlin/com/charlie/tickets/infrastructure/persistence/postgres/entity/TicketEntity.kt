package com.charlie.tickets.infrastructure.persistence.postgres.entity

import com.charlie.tickets.domain.types.TicketStatusEnum
import jakarta.persistence.CascadeType
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
import jakarta.persistence.OneToMany
import jakarta.persistence.Table
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import java.time.LocalDateTime
import java.util.UUID

@Entity
@Table(name = "tickets")
data class TicketEntity(
    @Id
    @Column(nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.UUID)
    val id: UUID,

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    val status: TicketStatusEnum,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ticket_type_id")
    val ticketTypeEntity: TicketTypeEntity,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "purchaser_id", nullable = false)
    val purchaser: UserEntity,

    @OneToMany(mappedBy = "ticketEntity", cascade = [CascadeType.ALL])
    val validations: List<TicketValidationEntity> = emptyList(),

    @OneToMany(mappedBy = "ticketEntity", cascade = [CascadeType.ALL])
    val qrCodeEntities: List<QrCodeEntity> = emptyList(),

    @CreatedDate
    @Column(name = "created_at", updatable = false, nullable = false)
    val createdAt: LocalDateTime,

    @LastModifiedDate
    @Column(name = "updated_at", nullable = false)
    val updatedAt: LocalDateTime
)
