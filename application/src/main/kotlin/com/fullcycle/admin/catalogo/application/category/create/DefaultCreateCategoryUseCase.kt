package com.fullcycle.admin.catalogo.application.category.create

import com.fullcycle.admin.catalogo.domain.category.Category
import com.fullcycle.admin.catalogo.domain.category.CategoryGateway
import com.fullcycle.admin.catalogo.domain.validation.handler.Notification
import io.vavr.API.Left
import io.vavr.API.Try
import io.vavr.control.Either
import java.util.*

class DefaultCreateCategoryUseCase(
    categoryGateway: CategoryGateway
) : CreateCategoryUseCase() {
    private val categoryGateway: CategoryGateway

    init {
        this.categoryGateway = Objects.requireNonNull(categoryGateway)
    }

    override fun execute(command: CreateCategoryCommand): Either<Notification, CreateCategoryOutput> {
        val notification = Notification.create()
        val category = Category.newCategory(
            name = command.name,
            description = command.description,
            isActive = command.isActive
        )
        category.validate(notification)

        return if (notification.hasErrors()) Left(notification) else create(category)
    }

    private fun create(category: Category): Either<Notification, CreateCategoryOutput> {
        return Try {
            categoryGateway.create(category)
        }
            .toEither()
            .bimap(Notification::create, CreateCategoryOutput::from)
    }
}
