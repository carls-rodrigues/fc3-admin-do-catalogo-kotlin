package com.fullcycle.admin.catalogo.application.category.create

import com.fullcycle.admin.catalogo.domain.category.Category
import com.fullcycle.admin.catalogo.domain.category.CategoryGateway
import com.fullcycle.admin.catalogo.domain.validation.handler.ThrowsValidationHandler
import java.util.*

class DefaultCreateCategoryUseCase(
    categoryGateway: CategoryGateway
) : CreateCategoryUseCase() {
    private val categoryGateway: CategoryGateway

    init {
        this.categoryGateway = Objects.requireNonNull(categoryGateway)
    }

    override fun execute(command: CreateCategoryCommand): CreateCategoryOutput {
        val category = Category.newCategory(
            name = command.name,
            description = command.description,
            isActive = command.isActive
        )
        category.validate(ThrowsValidationHandler())
        return CreateCategoryOutput.from(this.categoryGateway.create(category))
    }
}