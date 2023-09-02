package com.fullcycle.admin.catalogo.application.category.create

data class CreateCategoryCommand(
    val name: String,
    val description: String,
    val isActive: Boolean
) {
    companion object {
        fun with(
            name: String,
            description: String,
            isActive: Boolean
        ): CreateCategoryCommand {
            return CreateCategoryCommand(
                name = name,
                description = description,
                isActive = isActive
            )
        }
    }
}
