package com.charlie.tickets.domain.exceptions

import java.util.UUID

class UserNotFoundException(userId: UUID? = null) :
    RuntimeException(
        if (userId != null) "User with ID $userId not found" else "User not found"
    )