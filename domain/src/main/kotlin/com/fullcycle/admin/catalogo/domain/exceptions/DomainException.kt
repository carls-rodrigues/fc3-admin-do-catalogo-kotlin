package com.fullcycle.admin.catalogo.domain.exceptions
import com.fullcycle.admin.catalogo.domain.validation.Error
class DomainException private constructor(
    private val aMessage: String,
    private val errors: List<Error>
) : NoStacktraceException(aMessage) {
    companion object {
        fun with(error: Error): DomainException {
            return DomainException(error.message, listOf(error))
        }

        fun with(errors: List<Error>): DomainException {
            return DomainException("", errors)
        }
    }

    fun getErrors(): List<Error> {
        return this.errors
    }
}