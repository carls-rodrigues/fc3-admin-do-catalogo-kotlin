package com.fullcycle.admin.catalogo.application.category.update

data class UpdateCategoryCommand(
    val id: String,
    val name: String?,
    val description: String?,
    val isActive: Boolean
) {
    companion object {
        fun with(
            id: String,
            name: String?,
            description: String?,
            isActive: Boolean
        ): UpdateCategoryCommand {
            return UpdateCategoryCommand(
                id = id,
                name = name,
                description = description,
                isActive = isActive
            )
        }
    }
}
