package com.charlie.tickets.domain.ports.incoming

import com.charlie.tickets.domain.models.TokenPair
import com.charlie.tickets.domain.models.User

interface AuthUseCase {
    fun login(email: String, password: String): TokenPair

    fun logout(token: String): Boolean

    fun register(name: String, email: String, password: String): User

    fun refreshToken(token: String): TokenPair

}