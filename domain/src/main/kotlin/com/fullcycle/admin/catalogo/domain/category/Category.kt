package com.fullcycle.admin.catalogo.domain.category

import java.time.Instant
import java.util.UUID

class Category private constructor (
    private val id: String,
    private val name: String,
    private val description: String,
    private val isActive: Boolean,
    private val createdAt: Instant,
    private val updatedAt: Instant,
    private val deletedAt: Instant?
) {

    companion object {
        fun newCategory(
            name: String,
            description: String,
            isActive: Boolean

        ): Category {
            val id = UUID.randomUUID().toString()
            val now = Instant.now()
            return Category(
                id,
                name,
                description,
                isActive,
                createdAt = now,
                updatedAt = now,
                deletedAt = null
            )
        }
    }

    fun getId(): String {
        return this.id
    }

    fun name(): String {
        return this.name
    }

    fun getDescription(): String {
        return this.description
    }

    fun isActive(): Boolean {
        return this.isActive
    }

    fun getCreatedAt(): Instant {
        return this.createdAt
    }

    fun getUpdatedAt(): Instant {
        return this.updatedAt
    }

    fun getDeletedAt(): Instant? {
        return this.deletedAt
    }
}