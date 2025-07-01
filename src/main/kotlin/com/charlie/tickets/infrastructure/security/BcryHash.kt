package com.charlie.tickets.infrastructure.security

import com.charlie.tickets.domain.ports.outgoing.HashEncoder
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Component

@Component
class BcryHash : HashEncoder {

    private val bcrypt = BCryptPasswordEncoder()

    override fun encode(value: String): String = bcrypt.encode(value)

    override fun matches(rawValue: String, encodedValue: String): Boolean = bcrypt.matches(rawValue, encodedValue)
}