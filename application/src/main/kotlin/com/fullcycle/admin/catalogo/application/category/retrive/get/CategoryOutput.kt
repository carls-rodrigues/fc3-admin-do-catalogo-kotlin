package com.fullcycle.admin.catalogo.application.category.retrive.get

import com.fullcycle.admin.catalogo.domain.category.Category
import com.fullcycle.admin.catalogo.domain.category.CategoryID
import java.time.Instant

data class CategoryOutput(
    val id: CategoryID,
    val name: String,
    val description: String,
    val isActive: Boolean,
    val createdAt: Instant,
    val updatedAt: Instant,
    val deletedAt: Instant?
) {
    companion object {
        fun from(category: Category): CategoryOutput {
            return CategoryOutput(
                id = category.getId(),
                name = category.getName()!!,
                description = category.getDescription()!!,
                isActive = category.isActive(),
                createdAt = category.getCreatedAt(),
                updatedAt = category.getUpdatedAt(),
                deletedAt = category.getDeletedAt()
            )
        }
    }
}
