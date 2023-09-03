package com.fullcycle.admin.catalogo.application.category.retrive.list

import com.fullcycle.admin.catalogo.domain.category.Category
import com.fullcycle.admin.catalogo.domain.category.CategoryID
import java.time.Instant

data class CategoryListOutput(
    val id: CategoryID,
    val name: String,
    val description: String,
    val isActive: Boolean,
    val createdAt: Instant,
    val deletedAt: Instant?,
) {
    companion object {
        fun from(category: Category) = CategoryListOutput(
            id = category.getId(),
            name = category.getName()!!,
            description = category.getDescription()!!,
            isActive = category.isActive(),
            createdAt = category.getCreatedAt(),
            deletedAt = category.getDeletedAt()
        )
    }
}
