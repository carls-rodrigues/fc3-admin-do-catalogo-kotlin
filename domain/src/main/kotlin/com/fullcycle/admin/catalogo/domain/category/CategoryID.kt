package com.fullcycle.admin.catalogo.domain.category

import com.fullcycle.admin.catalogo.domain.Identifier
import java.util.*

class CategoryID private constructor(private val value: String) : Identifier() {

    init {
        Objects.requireNonNull(value, "CategoryID must not be null")
    }
    companion object {
        fun unique(): CategoryID {
            return from(UUID.randomUUID())
        }
        fun from(id: String): CategoryID {
            return CategoryID(id)
        }
        fun from(id: UUID): CategoryID {
            return CategoryID(id.toString().lowercase())
        }
    }

    fun getValue(): String {
        return value
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is CategoryID) return false

        if (value != other.value) return false

        return true
    }

    override fun hashCode(): Int {
        return value.hashCode()
    }


}