package com.fullcycle.admin.catalogo.domain.category

import com.fullcycle.admin.catalogo.domain.category.Category
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
        Assertions.assertEquals(expectedName, actualCategory.name())
        Assertions.assertEquals(expectedDescription, actualCategory.getDescription())
        Assertions.assertEquals(expectedIsActive, actualCategory.isActive())
        Assertions.assertNotNull(actualCategory.getCreatedAt())
        Assertions.assertNotNull(actualCategory.getUpdatedAt())
        Assertions.assertNull(actualCategory.getDeletedAt())
    }
}