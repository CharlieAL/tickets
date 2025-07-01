package com.charlie.tickets.domain.models

data class TokenPair(
    val accessToken: String,
    val refreshToken: String
)
