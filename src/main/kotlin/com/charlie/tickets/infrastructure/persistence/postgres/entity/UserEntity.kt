package com.charlie.tickets.infrastructure.persistence.postgres.entity

import com.charlie.tickets.domain.models.User
import jakarta.persistence.CascadeType
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.EntityListeners
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.JoinTable
import jakarta.persistence.ManyToMany
import jakarta.persistence.OneToMany
import jakarta.persistence.Table
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.LocalDateTime
import java.util.UUID

@Entity
@EntityListeners(AuditingEntityListener::class)
@Table(name = "users")
data class UserEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", updatable = false, nullable = false)
    val id: UUID? = null,

    val name: String,
    val email: String,
    val password: String,

    @OneToMany(mappedBy = "organizer", cascade = [CascadeType.ALL])
    val organizedEvents: List<EventEntity> = emptyList(),

    @ManyToMany
    @JoinTable(
        name = "user_attending_events",
        joinColumns = [JoinColumn(name = "user_id")],
        inverseJoinColumns = [JoinColumn(name = "event_id")]
    )
    val attendingEvents: List<EventEntity> = emptyList(),

    @ManyToMany
    @JoinTable(
        name = "user_staffing_events",
        joinColumns = [JoinColumn(name = "user_id")],
        inverseJoinColumns = [JoinColumn(name = "event_id")]
    )
    val staffingEvents: List<EventEntity> = emptyList(),

    @CreatedDate
    @Column(name = "created_at", updatable = false, nullable = false)
    val createdAt: LocalDateTime = LocalDateTime.now(),

    @LastModifiedDate
    @Column(name = "updated_at", nullable = false)
    val updatedAt: LocalDateTime = LocalDateTime.now()
)