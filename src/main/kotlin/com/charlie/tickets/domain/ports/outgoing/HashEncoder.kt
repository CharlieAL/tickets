package com.charlie.tickets.domain.ports.outgoing

interface HashEncoder {
    fun encode(value: String): String

    fun matches(rawValue: String, encodedValue: String): Boolean
}