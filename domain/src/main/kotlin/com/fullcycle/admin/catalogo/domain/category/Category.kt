package com.fullcycle.admin.catalogo.domain.category

import java.time.Instant
import java.util.UUID

class Category private constructor (
    id: String,
    name: String,
    description: String,
    isActive: Boolean,
    createdAt: Instant,
    updatedAt: Instant,
    deletedAt: Instant?
) {
    private val id: String = id
    private val name: String = name
    private val description: String = description
    private val isActive: Boolean = isActive
    private val createdAt: Instant = createdAt
    private val updatedAt: Instant = updatedAt
    private val deletedAt: Instant? = deletedAt


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