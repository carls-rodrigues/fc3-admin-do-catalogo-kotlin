package com.fullcycle.admin.catalogo.domain.category

import com.fullcycle.admin.catalogo.domain.exceptions.DomainException
import com.fullcycle.admin.catalogo.domain.validation.handler.ThrowsValidationHandler
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class CategoryTest {
    @Test
    fun given_a_valid_params_when_call_new_category_then_instantiate_a_Category() {
        val expectedName = "Filmes"
        val expectedDescription = "A categoria mais assistida"
        val expectedIsActive = true

        val actualCategory = Category.newCategory(
            name = expectedName,
            description = expectedDescription,
            isActive = expectedIsActive
        )

        Assertions.assertNotNull(actualCategory)
        Assertions.assertNotNull(actualCategory.getId())
        Assertions.assertEquals(expectedName, actualCategory.getName())
        Assertions.assertEquals(expectedDescription, actualCategory.getDescription())
        Assertions.assertEquals(expectedIsActive, actualCategory.isActive())
        Assertions.assertNotNull(actualCategory.getCreatedAt())
        Assertions.assertNotNull(actualCategory.getUpdatedAt())
        Assertions.assertNull(actualCategory.getDeletedAt())
    }
    @Test
    fun given_an_invalid_null_name_when_call_new_category_and_validate_then_should_receive_error() {
        val expectedName = null
        val expectedErrorMessage = "'name' should not be null"
        val expectedErrorCount =  1
        val expectedDescription = "A categoria mais assistida"
        val expectedIsActive = true

        val actualCategory = Category.newCategory(
            name = expectedName,
            description = expectedDescription,
            isActive = expectedIsActive
        )
        val handler = ThrowsValidationHandler()
        val actualException = Assertions.assertThrows(DomainException::class.java) {
            actualCategory.validate(handler)
        }

        Assertions.assertEquals(expectedErrorCount, actualException.getErrors().size)
        Assertions.assertEquals(expectedErrorMessage, actualException.getErrors()[0].message)
    }
}