package com.charlie.tickets.infrastructure.persistence.postgres.entity

import com.charlie.tickets.domain.types.EventStatusEnum
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
import jakarta.persistence.ManyToMany
import jakarta.persistence.ManyToOne
import jakarta.persistence.OneToMany
import jakarta.persistence.Table
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import java.time.Instant
import java.time.LocalDateTime
import java.util.UUID

@Entity
@Table(name = "events")
data class EventEntity(
    @Id
    @Column(updatable = false, nullable = false)
    @GeneratedValue(strategy = GenerationType.UUID)
    val id: UUID? = null,

    @Column(nullable = false)
    val name: String,

    @Column(name = "event_start")
    val eventStart: LocalDateTime,

    @Column(name = "event_end")
    val eventEnd: LocalDateTime,

    @Column(nullable = false)
    val venue: String,

    @Column(name = "sales_start")
    val salesStart: LocalDateTime,

    @Column(name = "sales_end")
    val salesEnd: LocalDateTime,

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    val status: EventStatusEnum,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "organizer_id", nullable = false)
    val organizer: UserEntity,

    @ManyToMany(mappedBy = "attendingEvents")
    val attendees: List<UserEntity> = emptyList(),

    @ManyToMany(mappedBy = "staffingEvents")
    val staff: List<UserEntity> = emptyList(),

    @OneToMany(mappedBy = "event", cascade = [CascadeType.ALL])
    val ticketTypeEntities: List<TicketTypeEntity> = emptyList(),

    @CreatedDate
    @Column(name = "created_at", updatable = false, nullable = false)
    val createdAt: LocalDateTime,

    @LastModifiedDate
    @Column(name = "updated_at", nullable = false)
    val updatedAt: LocalDateTime
)