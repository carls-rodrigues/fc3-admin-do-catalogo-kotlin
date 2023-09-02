package com.fullcycle.admin.catalogo.domain.exceptions
import com.fullcycle.admin.catalogo.domain.validation.Error
class DomainException private constructor(
    private val errors: List<Error>

): RuntimeException("", null, true, false) {
    companion object {
        fun with(errors: List<Error>): DomainException {
            return DomainException(errors)
        }
    }

    fun getErrors(): List<Error> {
        return this.errors
    }
}