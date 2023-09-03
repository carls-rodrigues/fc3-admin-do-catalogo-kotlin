package com.fullcycle.admin.catalogo.application.category.retrive.get

import com.fullcycle.admin.catalogo.domain.category.CategoryGateway
import com.fullcycle.admin.catalogo.domain.category.CategoryID
import com.fullcycle.admin.catalogo.domain.exceptions.DomainException
import com.fullcycle.admin.catalogo.domain.validation.Error
import io.vavr.kotlin.option

class DefaultGetCategoryByIdUseCase(
    private val categoryGateway: CategoryGateway
) : GetCategoryByIdUseCase() {
    override fun execute(command: String): CategoryOutput {
        val anId = CategoryID.from(command)
        return this.categoryGateway.findByID(anId).let { category ->
            category.option().map { CategoryOutput.from(it) }.getOrElseThrow { notFound(anId) }
        }
    }

    private fun notFound(id: CategoryID): Throwable {
        throw DomainException.with(Error("Category with ID %s was not found".format(id.getValue())))
    }
}