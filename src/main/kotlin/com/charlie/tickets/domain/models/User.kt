package com.charlie.tickets.domain.models

import jakarta.persistence.CascadeType
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.JoinTable
import jakarta.persistence.ManyToMany
import jakarta.persistence.OneToMany
import jakarta.persistence.Table
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import java.time.LocalDateTime
import java.util.UUID

@Entity
@Table(name = "users")
data class User(
    @Id
    @Column(name = "id", updatable = false, nullable = false)
    val id: UUID,

    val name: String,
    val email: String,

    @OneToMany(mappedBy = "organizer", cascade = [CascadeType.ALL])
    val organizedEvents: List<Event> = emptyList(),

    @ManyToMany
    @JoinTable(
        name = "user_attending_events",
        joinColumns = [JoinColumn(name = "user_id")],
        inverseJoinColumns = [JoinColumn(name = "event_id")]
    )
    val attendingEvents: List<Event> = emptyList(),

    @ManyToMany
    @JoinTable(
        name = "user_staffing_events",
        joinColumns = [JoinColumn(name = "user_id")],
        inverseJoinColumns = [JoinColumn(name = "event_id")]
    )
    val staffingEvents: List<Event> = emptyList(),

    @CreatedDate
    @Column(name = "created_at", updatable = false, nullable = false)
    val createdAt: LocalDateTime = LocalDateTime.now(),

    @LastModifiedDate
    @Column(name = "updated_at", nullable = false)
    val updatedAt: LocalDateTime = LocalDateTime.now()
)