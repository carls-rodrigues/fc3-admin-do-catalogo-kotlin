package com.fullcycle.admin.catalogo.domain.category

import com.fullcycle.admin.catalogo.domain.AggregateRoot
import com.fullcycle.admin.catalogo.domain.validation.ValidationHandler
import java.time.Instant

class Category private constructor (
    id: CategoryID,
    private var name: String?,
    private var description: String?,
    private var isActive: Boolean,
    private val createdAt: Instant,
    private var updatedAt: Instant,
    private var deletedAt: Instant?
) : AggregateRoot<CategoryID>(id), Cloneable {

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

    fun activate(): Category {
        this.deletedAt = null
        this.isActive = true
        this.updatedAt = Instant.now()
        return this
    }

    fun deactivate(): Category {
        if (getDeletedAt() == null) {
            this.deletedAt = Instant.now()
        }
        this.isActive = false
        this.updatedAt = Instant.now()
        return this
    }

    fun update(
        name: String?,
        description: String?,
        isActive: Boolean
    ): Category {
        if (isActive) {
            activate()
        } else {
            deactivate()
        }
        this.name = name
        this.description = description
        this.updatedAt = Instant.now()
        return this
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

    public override fun clone(): Category {
        try {
            return super.clone() as Category
        } catch (e: CloneNotSupportedException) {
            throw AssertionError(e)
        }
    }

    override fun validate(handler: ValidationHandler) {
        CategoryValidator(this, handler).validate()
    }
}