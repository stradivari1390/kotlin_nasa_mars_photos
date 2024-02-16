package com.example.kotlin_test.controller

import org.springframework.http.ResponseEntity
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.client.RestClientException
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException


@ControllerAdvice
class GlobalExceptionHandler {
    @ExceptionHandler(RestClientException::class)
    fun handleRestClientException(e: RestClientException, model: Model): String {
        model.addAttribute("error", "Failed to fetch data from NASA API: " + e.message)
        return "error"
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException::class)
    fun handleTypeMismatch(exception: MethodArgumentTypeMismatchException): ResponseEntity<String> {
        println("MethodArgumentTypeMismatchException: ${exception.message}")
        return ResponseEntity.badRequest().body("Invalid parameter: ${exception.message}")
    }
}