package com.charlie.tickets.domain.models

import com.charlie.tickets.domain.EventStatusEnum
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
import jakarta.persistence.Table
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import java.time.LocalDateTime
import java.util.UUID

@Entity
@Table(name = "events")
data class Event(
    @Id
    @Column(updatable = false, nullable = false)
    @GeneratedValue(strategy = GenerationType.UUID)
    val id: UUID,
    @Column(nullable = false)
    val name: String,
    val start: LocalDateTime,
    val end: LocalDateTime,
    @Column(nullable = false)
    val venue: String,
    val salesStart: LocalDateTime,
    val salesEnd: LocalDateTime,
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    val status: EventStatusEnum,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "organizer_id", nullable = false)
    val organizer: User,

    @ManyToMany(mappedBy = "attendingEvents")
    val attendees: List<User> = emptyList(),
    
    @ManyToMany(mappedBy = "staffingEvents")
    val staff: List<User> = emptyList(),

    @CreatedDate
    @Column(name = "created_at", updatable = false, nullable = false)
    val createdAt: LocalDateTime,

    @LastModifiedDate
    @Column(name = "updated_at", nullable = false)
    val updatedAt: LocalDateTime


)