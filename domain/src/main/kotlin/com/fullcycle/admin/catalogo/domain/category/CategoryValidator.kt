package com.fullcycle.admin.catalogo.domain.category

import com.fullcycle.admin.catalogo.domain.validation.Error
import com.fullcycle.admin.catalogo.domain.validation.ValidationHandler
import com.fullcycle.admin.catalogo.domain.validation.Validator

class CategoryValidator(
    private val category: Category,
    validationHandler: ValidationHandler
): Validator(validationHandler) {
    override fun validate() {
        if (this.category.getName() == null) {
            this.validationHandler().append(Error("'name' should not be null"))
        }
    }
}