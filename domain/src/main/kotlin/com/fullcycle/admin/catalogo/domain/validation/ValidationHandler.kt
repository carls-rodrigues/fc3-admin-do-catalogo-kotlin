package com.fullcycle.admin.catalogo.domain.validation

interface ValidationHandler {
    fun append(error: Error): ValidationHandler
    fun append(handler: ValidationHandler): ValidationHandler
    fun validate(validation: Validation): ValidationHandler

    fun hasErrors(): Boolean {
        return getErrors().isNotEmpty()
    }

    fun getErrors(): List<Error>
    interface Validation {
        fun validate()
    }

}