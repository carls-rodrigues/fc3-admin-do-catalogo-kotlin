package com.fullcycle.admin.catalogo.application.category.retrieve.get

import com.fullcycle.admin.catalogo.application.category.retrive.get.DefaultGetCategoryByIdUseCase
import com.fullcycle.admin.catalogo.domain.category.Category
import com.fullcycle.admin.catalogo.domain.category.CategoryGateway
import com.fullcycle.admin.catalogo.domain.category.CategoryID
import com.fullcycle.admin.catalogo.domain.exceptions.DomainException
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.jupiter.MockitoExtension
import org.mockito.kotlin.eq
import org.mockito.kotlin.reset
import org.mockito.kotlin.whenever

@ExtendWith(MockitoExtension::class)
class GetCategoryByIdUseCaseTest {

    @InjectMocks
    private lateinit var useCase: DefaultGetCategoryByIdUseCase

    @Mock
    private lateinit var categoryGateway: CategoryGateway

    @BeforeEach
    fun cleanUp() {
        reset(categoryGateway)
    }

    @Test
    fun `given a valid id when calls GetCategory should return Category`() {
        val expectedName = "Filmes"
        val expectedDescription = "A categoria mais assistida"
        val expectedIsActive = true

        val category = Category.newCategory(expectedName, expectedDescription, expectedIsActive)
        val expectedId = category.getId()

        whenever(categoryGateway.findByID(eq(expectedId))).thenReturn(category.clone())

        val actualCategory = useCase.execute(expectedId.getValue())

        Assertions.assertEquals(expectedId, actualCategory.id)
        Assertions.assertEquals(expectedName, actualCategory.name)
        Assertions.assertEquals(expectedDescription, actualCategory.description)
        Assertions.assertEquals(expectedIsActive, actualCategory.isActive)
        Assertions.assertEquals(category.getCreatedAt(), actualCategory.createdAt)
        Assertions.assertEquals(category.getUpdatedAt(), actualCategory.updatedAt)
        Assertions.assertEquals(category.getDeletedAt(), actualCategory.deletedAt)

    }

    @Test
    fun `given a invalid id when calls GetCategory should return NotFound`() {
        val expectedErrorMessage = "Category with ID 123 was not found"
        val expectedId = CategoryID.from("123")

        whenever(categoryGateway.findByID(eq(expectedId))).thenReturn(null)

        val actualException = Assertions.assertThrows(DomainException::class.java) {
            useCase.execute(expectedId.getValue())
        }

        Assertions.assertEquals(expectedErrorMessage, actualException.message)

    }

    @Test
    fun `given a valid id when Gateway Throws Exception then should return Exception`() {
        val expectedErrorMessage = "Gateway error"
        val expectedId = CategoryID.from("123")

        whenever(categoryGateway.findByID(eq(expectedId))).thenThrow(IllegalStateException("Gateway error"))

        val actualException = Assertions.assertThrows(IllegalStateException::class.java) {
            useCase.execute(expectedId.getValue())
        }

        Assertions.assertEquals(expectedErrorMessage, actualException.message)

    }
}