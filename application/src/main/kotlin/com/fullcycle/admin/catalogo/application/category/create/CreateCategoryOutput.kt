package com.fullcycle.admin.catalogo.application.category.create

import com.fullcycle.admin.catalogo.domain.category.Category
import com.fullcycle.admin.catalogo.domain.category.CategoryID

data class CreateCategoryOutput(
    val id: CategoryID
) {
    companion object {
        fun from(category: Category): CreateCategoryOutput {
            return CreateCategoryOutput(
                id = category.getId()
            )
        }
    }
}
