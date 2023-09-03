package com.fullcycle.admin.catalogo.application.category.retrive.list

import com.fullcycle.admin.catalogo.application.UseCase
import com.fullcycle.admin.catalogo.domain.category.CategorySearchQuery
import com.fullcycle.admin.catalogo.domain.pagination.Pagination

abstract class ListCategoriesUseCase : UseCase<CategorySearchQuery, Pagination<CategoryListOutput>>()