package com.fullcycle.admin.catalogo.domain.exceptions

open class NoStacktraceException @JvmOverloads constructor(message: String?, cause: Throwable? = null) :
    RuntimeException(message, cause, true, false)
