package com.fullcycle.admin.catalogo.domain.validation.handler

import com.fullcycle.admin.catalogo.domain.exceptions.DomainException
import com.fullcycle.admin.catalogo.domain.validation.Error
import com.fullcycle.admin.catalogo.domain.validation.ValidationHandler

class Notification private constructor(
    private val errors: MutableList<Error> = mutableListOf()
) : ValidationHandler {

    companion object {
        fun create(): Notification = Notification(ArrayList())
        fun create(error: Error): Notification = Notification(ArrayList()).append(error)
        fun create(t: Throwable): Notification {
            return create(Error(t.message ?: ""))
        }
    }

    override fun append(error: Error): Notification {
        this.errors.add(error)
        return this
    }

    override fun append(handler: ValidationHandler): Notification {
        this.errors.addAll(handler.getErrors())
        return this
    }

    override fun validate(validation: ValidationHandler.Validation): Notification {
        try {
            validation.validate()
        } catch (ex: DomainException) {
            this.errors.addAll(ex.getErrors())
        } catch (t: Throwable) {
            this.errors.add(Error(t.message ?: ""))
        }
        return this
    }

    override fun getErrors(): List<Error> {
        return this.errors
    }
}
