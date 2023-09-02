package com.fullcycle.admin.catalogo.domain.category

import com.fullcycle.admin.catalogo.domain.AggregateRoot
import com.fullcycle.admin.catalogo.domain.validation.ValidationHandler
import java.time.Instant

class Category private constructor (
    id: CategoryID,
    private val name: String?,
    private val description: String?,
    private val isActive: Boolean,
    private val createdAt: Instant,
    private val updatedAt: Instant,
    private val deletedAt: Instant?
) : AggregateRoot<CategoryID> (id) {

    companion object {
        fun newCategory(
            name: String?,
            description: String?,
            isActive: Boolean

        ): Category {
            val id = CategoryID.unique()
            val now = Instant.now()
            return Category(
                id,
                name,
                description,
                isActive,
                createdAt = now,
                updatedAt = now,
                deletedAt = if (!isActive) now else null
            )
        }
    }

    fun getName(): String? {
        return this.name
    }

    fun getDescription(): String? {
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

    override fun validate(handler: ValidationHandler) {
        CategoryValidator(this, handler).validate()
    }
}