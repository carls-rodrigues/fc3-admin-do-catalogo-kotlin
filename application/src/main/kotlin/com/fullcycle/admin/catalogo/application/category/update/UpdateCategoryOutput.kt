package com.fullcycle.admin.catalogo.application.category.update

import com.fullcycle.admin.catalogo.domain.category.Category
import com.fullcycle.admin.catalogo.domain.category.CategoryID

data class UpdateCategoryOutput(
    val id: CategoryID
) {
    companion object {
        fun from(category: Category): UpdateCategoryOutput {
            return UpdateCategoryOutput(id = category.getId())
        }
    }
}
