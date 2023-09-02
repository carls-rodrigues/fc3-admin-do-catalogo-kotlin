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

    @Test
    fun given_an_invalid_empty_name_when_call_new_category_and_validate_then_should_receive_error() {
        val expectedName = "  "
        val expectedErrorMessage = "'name' should not be empty"
        val expectedErrorCount = 1
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

    @Test
    fun given_an_invalid_name_length_less_than_3_when_call_new_category_and_validate_then_should_receive_error() {
        val expectedName = "Fi "
        val expectedErrorMessage = "'name' must be between 3 and 255 characters"
        val expectedErrorCount = 1
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

    @Test
    fun given_an_invalid_name_length_more_than_255_when_call_new_category_and_validate_then_should_receive_error() {
        val expectedName = "a".repeat(256)
        val expectedErrorMessage = "'name' must be between 3 and 255 characters"
        val expectedErrorCount = 1
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

    @Test
    fun given_an_valid_empty_description_when_call_new_category_and_validate_then_should_receive_error() {
        val expectedName = "Filmes"
        val expectedDescription = "   "
        val expectedIsActive = true

        val actualCategory = Category.newCategory(
            name = expectedName,
            description = expectedDescription,
            isActive = expectedIsActive
        )

        Assertions.assertDoesNotThrow() {
            actualCategory.validate(ThrowsValidationHandler())
        }

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
    fun given_an_valid_false_is_active_when_call_new_category_and_validate_then_should_receive_error() {
        val expectedName = "Filmes"
        val expectedDescription = "A categoria mais assistida"
        val expectedIsActive = false

        val actualCategory = Category.newCategory(
            name = expectedName,
            description = expectedDescription,
            isActive = expectedIsActive
        )

        Assertions.assertDoesNotThrow() {
            actualCategory.validate(ThrowsValidationHandler())
        }

        Assertions.assertNotNull(actualCategory)
        Assertions.assertNotNull(actualCategory.getId())
        Assertions.assertEquals(expectedName, actualCategory.getName())
        Assertions.assertEquals(expectedDescription, actualCategory.getDescription())
        Assertions.assertEquals(expectedIsActive, actualCategory.isActive())
        Assertions.assertNotNull(actualCategory.getCreatedAt())
        Assertions.assertNotNull(actualCategory.getUpdatedAt())
        Assertions.assertNotNull(actualCategory.getDeletedAt())
    }

    @Test
    fun given_a_valid_active_category_when_call_deactivate_then_return_category_inactivated() {
        val expectedName = "Filmes"
        val expectedDescription = "A categoria mais assistida"
        val expectedIsActive = false

        val aCategory = Category.newCategory(
            name = expectedName,
            description = expectedDescription,
            isActive = true
        )

        Assertions.assertDoesNotThrow() {
            aCategory.validate(ThrowsValidationHandler())
        }

        val createdAt = aCategory.getCreatedAt()
        val updatedAt = aCategory.getUpdatedAt()

        Assertions.assertTrue(aCategory.isActive())
        Assertions.assertNull(aCategory.getDeletedAt())

        val actualCategory = aCategory.deactivate()

        Assertions.assertDoesNotThrow() {
            aCategory.validate(ThrowsValidationHandler())
        }

        Assertions.assertEquals(actualCategory.getId(), aCategory.getId())
        Assertions.assertEquals(expectedName, actualCategory.getName())
        Assertions.assertEquals(expectedDescription, actualCategory.getDescription())
        Assertions.assertEquals(expectedIsActive, actualCategory.isActive())
        Assertions.assertEquals(createdAt, actualCategory.getCreatedAt())
        Assertions.assertTrue(actualCategory.getUpdatedAt().isAfter(updatedAt))
        Assertions.assertNotNull(actualCategory.getDeletedAt())
    }

    @Test
    fun given_a_valid_inactive_category_when_call_activate_then_return_category_activated() {
        val expectedName = "Filmes"
        val expectedDescription = "A categoria mais assistida"
        val expectedIsActive = true

        val aCategory = Category.newCategory(
            name = expectedName,
            description = expectedDescription,
            isActive = false
        )

        Assertions.assertDoesNotThrow() {
            aCategory.validate(ThrowsValidationHandler())
        }

        val createdAt = aCategory.getCreatedAt()
        val updatedAt = aCategory.getUpdatedAt()

        Assertions.assertFalse(aCategory.isActive())
        Assertions.assertNotNull(aCategory.getDeletedAt())

        val actualCategory = aCategory.activate()

        Assertions.assertDoesNotThrow() {
            aCategory.validate(ThrowsValidationHandler())
        }

        Assertions.assertEquals(actualCategory.getId(), aCategory.getId())
        Assertions.assertEquals(expectedName, actualCategory.getName())
        Assertions.assertEquals(expectedDescription, actualCategory.getDescription())
        Assertions.assertEquals(expectedIsActive, actualCategory.isActive())
        Assertions.assertEquals(createdAt, actualCategory.getCreatedAt())
        Assertions.assertTrue(actualCategory.getUpdatedAt().isAfter(updatedAt))
        Assertions.assertNull(actualCategory.getDeletedAt())
    }

    @Test
    fun given_a_valid_category_when_call_update_then_return_category_updated() {
        val expectedName = "Filmes"
        val expectedDescription = "A categoria mais assistida"
        val expectedIsActive = true

        val aCategory = Category.newCategory(
            name = "Film",
            description = "A categoria",
            isActive = expectedIsActive
        )

        Assertions.assertDoesNotThrow() {
            aCategory.validate(ThrowsValidationHandler())
        }

        val createdAt = aCategory.getCreatedAt()
        val updatedAt = aCategory.getUpdatedAt()

        val actualCategory = aCategory.update(
            name = expectedName,
            description = expectedDescription,
            isActive = expectedIsActive
        )

        Assertions.assertDoesNotThrow() {
            aCategory.validate(ThrowsValidationHandler())
        }

        Assertions.assertEquals(actualCategory.getId(), aCategory.getId())
        Assertions.assertEquals(expectedName, actualCategory.getName())
        Assertions.assertEquals(expectedDescription, actualCategory.getDescription())
        Assertions.assertEquals(expectedIsActive, actualCategory.isActive())
        Assertions.assertEquals(createdAt, actualCategory.getCreatedAt())
        Assertions.assertTrue(actualCategory.getUpdatedAt().isAfter(updatedAt))
        Assertions.assertNull(actualCategory.getDeletedAt())
    }


    @Test
    fun given_a_valid_category_when_call_update_to_inactivate_then_should_return_category_updated() {
        val expectedName = "Filmes"
        val expectedDescription = "A categoria mais assistida"
        val expectedIsActive = false

        val aCategory = Category.newCategory(
            name = "Film",
            description = "A categoria",
            isActive = true
        )

        Assertions.assertDoesNotThrow() {
            aCategory.validate(ThrowsValidationHandler())
        }

        Assertions.assertTrue(aCategory.isActive())
        Assertions.assertNull(aCategory.getDeletedAt())

        val createdAt = aCategory.getCreatedAt()
        val updatedAt = aCategory.getUpdatedAt()

        val actualCategory = aCategory.update(
            name = expectedName,
            description = expectedDescription,
            isActive = expectedIsActive
        )

        Assertions.assertDoesNotThrow() {
            aCategory.validate(ThrowsValidationHandler())
        }

        Assertions.assertEquals(actualCategory.getId(), aCategory.getId())
        Assertions.assertEquals(expectedName, actualCategory.getName())
        Assertions.assertEquals(expectedDescription, actualCategory.getDescription())
        Assertions.assertFalse(actualCategory.isActive())
        Assertions.assertEquals(createdAt, actualCategory.getCreatedAt())
        Assertions.assertTrue(actualCategory.getUpdatedAt().isAfter(updatedAt))
        Assertions.assertNotNull(actualCategory.getDeletedAt())
    }


    @Test
    fun given_a_valid_category_when_call_update_with_invalid_param_then_should_return_category_updated() {
        val expectedName = null
        val expectedDescription = "A categoria mais assistida"
        val expectedIsActive = true

        val aCategory = Category.newCategory(
            name = "Film",
            description = "A categoria",
            isActive = true
        )

        Assertions.assertDoesNotThrow() {
            aCategory.validate(ThrowsValidationHandler())
        }

        val createdAt = aCategory.getCreatedAt()
        val updatedAt = aCategory.getUpdatedAt()

        val actualCategory = aCategory.update(
            name = expectedName,
            description = expectedDescription,
            isActive = expectedIsActive
        )

        Assertions.assertEquals(actualCategory.getId(), aCategory.getId())
        Assertions.assertEquals(expectedName, actualCategory.getName())
        Assertions.assertEquals(expectedDescription, actualCategory.getDescription())
        Assertions.assertTrue(actualCategory.isActive())
        Assertions.assertEquals(createdAt, actualCategory.getCreatedAt())
        Assertions.assertTrue(actualCategory.getUpdatedAt().isAfter(updatedAt))
        Assertions.assertNull(actualCategory.getDeletedAt())
    }
}