package com.charlie.tickets.infrastructure.persistence.postgres.entity

import jakarta.persistence.CascadeType
import jakarta.persistence.Column
import jakarta.persistence.Entity
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
@Table(name = "ticket_types")
data class TicketTypeEntity(
    @Id
    @Column(nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.UUID)
    val id: UUID? = null,

    @Column(nullable = false)
    val name: String,

    @Column(nullable = false)
    val price: Double,

    @Column(nullable = false)
    val description: String,

    @Column(name = "total_available")
    val totalAvailable: Int,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "event_id")
    val event: EventEntity? = null,

    @OneToMany(mappedBy = "ticketTypeEntity", cascade = [CascadeType.ALL])
    val ticketEntities: List<TicketEntity> = emptyList(),

    @CreatedDate
    @Column(name = "created_at", updatable = false, nullable = false)
    val createdAt: LocalDateTime,

    @LastModifiedDate
    @Column(name = "updated_at", nullable = false)
    val updatedAt: LocalDateTime
)

