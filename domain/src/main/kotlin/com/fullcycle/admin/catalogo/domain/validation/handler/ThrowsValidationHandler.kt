package com.fullcycle.admin.catalogo.domain.validation.handler

import com.fullcycle.admin.catalogo.domain.exceptions.DomainException
import com.fullcycle.admin.catalogo.domain.validation.Error
import com.fullcycle.admin.catalogo.domain.validation.ValidationHandler

class ThrowsValidationHandler: ValidationHandler {
    override fun append(error: Error): ValidationHandler {
        throw DomainException.with(listOf(error))
    }

    override fun append(handler: ValidationHandler): ValidationHandler {
        throw DomainException.with(handler.getErrors())
    }

    override fun validate(validation: ValidationHandler.Validation): ValidationHandler {
        try {
            validation.validate()
        } catch (e: Exception) {
            throw DomainException.with(listOf(Error(e.message ?: "Unknown error")))
        }
        return this
    }

    override fun getErrors(): List<Error> {
        TODO("Not yet implemented")
    }
}