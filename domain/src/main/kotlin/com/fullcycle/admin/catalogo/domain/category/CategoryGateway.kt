package com.fullcycle.admin.catalogo.domain.category

import com.fullcycle.admin.catalogo.domain.pagination.Pagination

interface CategoryGateway {
    fun create(category: Category): Category
    fun deleteByID(id: CategoryID)
    fun findByID(id: CategoryID): Category?
    fun update(category: Category): Category
    fun findAll(query: CategorySearchQuery): Pagination<Category>
}