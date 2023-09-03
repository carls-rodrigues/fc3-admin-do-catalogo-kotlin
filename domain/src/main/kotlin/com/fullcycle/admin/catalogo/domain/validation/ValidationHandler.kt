package com.fullcycle.admin.catalogo.domain.validation

interface ValidationHandler {
    fun append(error: Error): ValidationHandler
    fun append(handler: ValidationHandler): ValidationHandler
    fun validate(validation: Validation): ValidationHandler

    fun hasErrors(): Boolean {
        return getErrors().isNotEmpty()
    }

    fun firstError(): Error? {
        return if (getErrors().isNotEmpty()) getErrors()[0] else null
    }

    fun getErrors(): List<Error>
    interface Validation {
        fun validate()
    }

}
