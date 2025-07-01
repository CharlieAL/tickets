package com.charlie.tickets.infrastructure.web.exceptions

import com.charlie.tickets.domain.exceptions.UserNotFoundException
import jakarta.validation.ConstraintViolationException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun handleMethodArgumentNotValid(ex: MethodArgumentNotValidException): ResponseEntity<ErrorResponse> {
        val message = ex.bindingResult.fieldErrors.joinToString("; ") {
            "${it.field}: ${it.defaultMessage}"
        }
        return ResponseEntity
            .status(HttpStatus.BAD_REQUEST)
            .body(ErrorResponse("VALIDATION_ERROR", message))
    }

    @ExceptionHandler(ConstraintViolationException::class)
    fun handleConstraintViolation(ex: ConstraintViolationException): ResponseEntity<ErrorResponse> {
        val message = ex.constraintViolations.joinToString("; ") {
            "${it.propertyPath}: ${it.message}"
        }
        return ResponseEntity
            .status(HttpStatus.BAD_REQUEST)
            .body(ErrorResponse("VALIDATION_ERROR", message))
    }


    @ExceptionHandler(UserNotFoundException::class)
    fun handleUserNotFound(ex: UserNotFoundException): ResponseEntity<ErrorResponse> {
        return ResponseEntity
            .status(HttpStatus.NOT_FOUND)
            .body(ErrorResponse("USER_NOT_FOUND", ex.message ?: "Usuario no encontrado"))
    }

    // Manejo genérico para excepciones inesperadas
//    @ExceptionHandler(Exception::class)
//    fun handleGeneric(ex: Exception): ResponseEntity<ErrorResponse> {
//        return ResponseEntity
//            .status(HttpStatus.INTERNAL_SERVER_ERROR)
//            .body(ErrorResponse("INTERNAL_ERROR", ex.message ?: "Error inesperado"))
//    }
}