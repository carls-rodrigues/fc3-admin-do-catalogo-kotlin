package com.fullcycle.admin.catalogo.domain.pagination

data class Pagination<T>(
    val currentPage: Int,
    val perPage: Int,
    val total: Long,
    val list: List<T>
)
