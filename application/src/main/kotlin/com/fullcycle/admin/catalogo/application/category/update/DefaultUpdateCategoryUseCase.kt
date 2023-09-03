package com.fullcycle.admin.catalogo.application.category.update

import com.fullcycle.admin.catalogo.domain.category.Category
import com.fullcycle.admin.catalogo.domain.category.CategoryGateway
import com.fullcycle.admin.catalogo.domain.category.CategoryID
import com.fullcycle.admin.catalogo.domain.exceptions.DomainException
import com.fullcycle.admin.catalogo.domain.validation.Error
import com.fullcycle.admin.catalogo.domain.validation.handler.Notification
import io.vavr.API.Left
import io.vavr.API.Try
import io.vavr.control.Either

class DefaultUpdateCategoryUseCase(
    private val categoryGateway: CategoryGateway
) : UpdateCategoryUseCase() {
    override fun execute(command: UpdateCategoryCommand): Either<Notification, UpdateCategoryOutput> {
        val id = CategoryID.from(command.id)
        val category = this.categoryGateway.findByID(id) ?: throw notFound(id)
        val notification = Notification.create()
        val updatedCategory = category.update(
            name = command.name,
            description = command.description,
            isActive = command.isActive
        )
        updatedCategory.validate(notification)
        return if (notification.hasErrors()) Left(notification) else update(updatedCategory)
    }

    private fun update(category: Category): Either<Notification, UpdateCategoryOutput> {
        return Try {
            this.categoryGateway.update(category)
        }
            .toEither()
            .bimap(Notification::create, UpdateCategoryOutput::from)
    }

    private fun notFound(id: CategoryID): Throwable {
        throw DomainException.with(Error("Category with ID %s was not found".format(id.getValue())))
    }
}