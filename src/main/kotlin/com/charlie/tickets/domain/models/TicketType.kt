package com.charlie.tickets.domain.models

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
data class TicketType(
    @Id
    @Column(nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.UUID)
    val id: UUID,

    @Column(nullable = false)
    val name: String,

    @Column(nullable = false)
    val price: Double,
    
    @Column(name = "total_available")
    val totalAvailable: Int,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "event_id", nullable = false)
    val event: Event,

    @OneToMany(mappedBy = "ticketType", cascade = [CascadeType.ALL])
    val tickets: List<Ticket> = emptyList(),

    @CreatedDate
    @Column(name = "created_at", updatable = false, nullable = false)
    val createdAt: LocalDateTime,

    @LastModifiedDate
    @Column(name = "updated_at", nullable = false)
    val updatedAt: LocalDateTime
)
