package com.fullcycle.admin.catalogo.domain.category

import com.fullcycle.admin.catalogo.domain.validation.Error
import com.fullcycle.admin.catalogo.domain.validation.ValidationHandler
import com.fullcycle.admin.catalogo.domain.validation.Validator

class CategoryValidator(
    private val category: Category,
    validationHandler: ValidationHandler
): Validator(validationHandler) {

    companion object {
        private const val NAME_MIN_LENGTH = 3
        private const val NAME_MAX_LENGTH = 255
    }
    override fun validate() {
        checkNameConstraints()
    }

    private fun checkNameConstraints() {
        val name = this.category.getName()
        if (name == null) {
            this.validationHandler().append(Error("'name' should not be null"))
            return
        }
        if (name.isBlank()) {
            this.validationHandler().append(Error("'name' should not be empty"))
            return
        }
        val length = name.trim().length
        if (length < NAME_MIN_LENGTH || length > NAME_MAX_LENGTH) {
            this.validationHandler().append(Error("'name' must be between 3 and 255 characters"))
            return
        }
    }
}