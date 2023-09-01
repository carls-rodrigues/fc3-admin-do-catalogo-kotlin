package com.fullcycle.admin.catalogo.application

import com.fullcycle.admin.catalogo.domain.Category

class UseCase {
    fun execute(): Category {
        return Category()
    }
}