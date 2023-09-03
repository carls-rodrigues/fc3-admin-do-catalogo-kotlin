package com.fullcycle.admin.catalogo.application.category.retrive.list

import com.fullcycle.admin.catalogo.domain.category.CategoryGateway
import com.fullcycle.admin.catalogo.domain.category.CategorySearchQuery
import com.fullcycle.admin.catalogo.domain.pagination.Pagination

class DefaultListCategoriesUseCase(
    private val categoryGateway: CategoryGateway
) : ListCategoriesUseCase() {
    override fun execute(aQuery: CategorySearchQuery): Pagination<CategoryListOutput> {
        return this.categoryGateway.findAll(aQuery)
            .map(CategoryListOutput::from)
    }
}