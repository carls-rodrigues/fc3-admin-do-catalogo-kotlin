package com.fullcycle.admin.catalogo.domain.pagination

data class Pagination<T>(
    val currentPage: Int,
    val perPage: Int,
    val total: Long,
    val items: List<T>
) {
    fun <R> map(mapper: (T) -> R): Pagination<R> {
        val newList = this.items.stream().map(mapper).toList()
        return Pagination(this.currentPage, this.perPage, this.total, newList)
    }
}