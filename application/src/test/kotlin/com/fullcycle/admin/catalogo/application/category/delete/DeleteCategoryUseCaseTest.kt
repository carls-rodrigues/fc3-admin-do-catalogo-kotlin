package com.fullcycle.admin.catalogo.application.category.delete

import com.fullcycle.admin.catalogo.domain.category.Category
import com.fullcycle.admin.catalogo.domain.category.CategoryGateway
import com.fullcycle.admin.catalogo.domain.category.CategoryID
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.jupiter.MockitoExtension
import org.mockito.kotlin.*

@ExtendWith(MockitoExtension::class)
class DeleteCategoryUseCaseTest {

    @InjectMocks
    private lateinit var useCase: DefaultDeleteCategoryUseCase

    @Mock
    private lateinit var categoryGateway: CategoryGateway

    @BeforeEach
    fun cleanUp() {
        reset(categoryGateway)
    }

    @Test
    fun given_a_valid_id_when_calls_DeleteCategory_should_be_ok() {
        val category = Category.newCategory("Filmes", "A categoria mais assistida", true)
        val expectedId = category.getId()

        doNothing().whenever(categoryGateway).deleteByID(eq(expectedId))

        Assertions.assertDoesNotThrow { useCase.execute(expectedId.getValue()) }

        verify(categoryGateway, times(1)).deleteByID(eq(expectedId))
    }

    @Test
    fun given_an_invalid_id_when_calls_DeleteCategory_should_be_ok() {
        val expectedId = CategoryID.from("123")

        doNothing().whenever(categoryGateway).deleteByID(eq(expectedId))

        Assertions.assertDoesNotThrow { useCase.execute(expectedId.getValue()) }

        verify(categoryGateway, times(1)).deleteByID(eq(expectedId))

    }

    @Test
    fun given_a_valid_id_when_gateway_throws_error_should_return_exception() {
        val category = Category.newCategory("Filmes", "A categoria mais assistida", true)
        val expectedId = category.getId()

        doThrow(IllegalStateException("Gateway error")).whenever(categoryGateway).deleteByID(eq(expectedId))

        Assertions.assertThrows(IllegalStateException::class.java) {
            useCase.execute(expectedId.getValue())
        }

        verify(categoryGateway, times(1)).deleteByID(eq(expectedId))

    }
}