package com.fullcycle.admin.catalogo.application.category.update

import com.fullcycle.admin.catalogo.domain.category.Category
import com.fullcycle.admin.catalogo.domain.category.CategoryGateway
import com.fullcycle.admin.catalogo.domain.category.CategoryID
import com.fullcycle.admin.catalogo.domain.exceptions.DomainException
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.AdditionalAnswers.returnsFirstArg
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.jupiter.MockitoExtension
import org.mockito.kotlin.*
import java.util.*

@ExtendWith(MockitoExtension::class)
class UpdateCategoryUseCaseTest {
    @Mock
    private lateinit var categoryGateway: CategoryGateway;

    @InjectMocks
    private lateinit var useCase: DefaultUpdateCategoryUseCase;

    @Test
    fun given_an_valid_command_when_call_update_category_then_should_return_category_id() {
        val category = Category.newCategory("Film", null, true)
        val expectedName = "Filmes"
        val expectedDescription = "A categoria mais assistida"
        val expectedIsActive = true
        val expectedId = category.getId()

        val command = UpdateCategoryCommand.with(
            expectedId.getValue(),
            expectedName,
            expectedDescription,
            expectedIsActive
        )
        whenever(categoryGateway.findByID(eq(expectedId)))
            .thenReturn(category.clone())

        whenever(categoryGateway.update(any()))
            .thenAnswer(returnsFirstArg<CategoryGateway>())

        val actualOutput = useCase.execute(command).get()

        Assertions.assertNotNull(actualOutput)
        Assertions.assertNotNull(actualOutput.id)

        verify(categoryGateway, times(1)).findByID(eq(expectedId))
        verify(categoryGateway, times(1))
            .update(argThat { aUpdatedCategory ->
                Objects.equals(expectedName, aUpdatedCategory.getName())
                        && Objects.equals(expectedDescription, aUpdatedCategory.getDescription())
                        && Objects.equals(expectedIsActive, aUpdatedCategory.isActive())
                        && Objects.equals(expectedId, aUpdatedCategory.getId())
                        && Objects.equals(category.getCreatedAt(), aUpdatedCategory.getCreatedAt())
                        && category.getUpdatedAt().isBefore(aUpdatedCategory.getUpdatedAt())
                        && Objects.isNull(aUpdatedCategory.getDeletedAt())
            })
    }


    @Test
    fun given_an_invalid_name_when_call_UpdateCategory_then_should_return_DomainException() {
        val category = Category.newCategory("Film", null, true)
        val expectedName = null
        val expectedDescription = "A categoria mais assistida"
        val expectedIsActive = true
        val expectedId = category.getId()

        val expectedErrorMessage = "'name' should not be null"
        val expectedErrorCount = 1

        val command = UpdateCategoryCommand.with(
            expectedId.getValue(),
            expectedName,
            expectedDescription,
            expectedIsActive
        )

        whenever(categoryGateway.findByID(eq(expectedId)))
            .thenReturn(category.clone())

        val notification = useCase.execute(command).left
        Assertions.assertEquals(expectedErrorCount, notification.getErrors().size)
        Assertions.assertEquals(expectedErrorMessage, notification.firstError()?.message)

        verify(categoryGateway, times(0)).update(any())
    }

    @Test
    fun given_an_valid_inactivate_command_when_call_update_category_then_should_return_an_inactive_category_id() {
        val category = Category.newCategory("Film", null, true)

        Assertions.assertTrue(category.isActive())
        Assertions.assertNull(category.getDeletedAt())

        val expectedName = "Filmes"
        val expectedDescription = "A categoria mais assistida"
        val expectedIsActive = false
        val expectedId = category.getId()

        val command = UpdateCategoryCommand.with(
            expectedId.getValue(),
            expectedName,
            expectedDescription,
            expectedIsActive
        )
        whenever(categoryGateway.findByID(eq(expectedId)))
            .thenReturn(category.clone())

        whenever(categoryGateway.update(any()))
            .thenAnswer(returnsFirstArg<CategoryGateway>())

        val actualOutput = useCase.execute(command).get()

        Assertions.assertNotNull(actualOutput)
        Assertions.assertNotNull(actualOutput.id)

        verify(categoryGateway, times(1)).findByID(eq(expectedId))
        verify(categoryGateway, times(1))
            .update(argThat { aUpdatedCategory ->
                Objects.equals(expectedName, aUpdatedCategory.getName())
                        && Objects.equals(expectedDescription, aUpdatedCategory.getDescription())
                        && Objects.equals(expectedIsActive, aUpdatedCategory.isActive())
                        && Objects.equals(expectedId, aUpdatedCategory.getId())
                        && Objects.equals(category.getCreatedAt(), aUpdatedCategory.getCreatedAt())
                        && category.getUpdatedAt().isBefore(aUpdatedCategory.getUpdatedAt())
                        && Objects.nonNull(aUpdatedCategory.getDeletedAt())
            })
    }

    @Test
    fun given_an_valid_command_when_gateway_throws_random_exception_then_should_return_a_exception() {
        val category = Category.newCategory("Film", null, true)
        // given
        val expectedName = "Filmes"
        val expectedDescription = "A categoria mais assistida"
        val expectedIsActive = true
        val expectedId = category.getId()

        val expectedErrorMessage = "Gateway error"
        val expectedErrorCount = 1

        val command = UpdateCategoryCommand.with(
            expectedId.getValue(),
            expectedName,
            expectedDescription,
            expectedIsActive
        )
        whenever(categoryGateway.findByID(eq(expectedId)))
            .thenReturn(category.clone())


        whenever(categoryGateway.update(any()))
            .thenThrow(IllegalStateException(expectedErrorMessage))

        val notification = useCase.execute(command).left
        Assertions.assertEquals(expectedErrorCount, notification.getErrors().size)
        Assertions.assertEquals(expectedErrorMessage, notification.firstError()?.message)

        verify(categoryGateway, times(1))
            .update(argThat { aUpdatedCategory ->
                Objects.equals(expectedName, aUpdatedCategory.getName())
                        && Objects.equals(expectedDescription, aUpdatedCategory.getDescription())
                        && Objects.equals(expectedIsActive, aUpdatedCategory.isActive())
                        && Objects.equals(expectedId, aUpdatedCategory.getId())
                        && Objects.equals(category.getCreatedAt(), aUpdatedCategory.getCreatedAt())
                        && category.getUpdatedAt().isBefore(aUpdatedCategory.getUpdatedAt())
                        && Objects.isNull(aUpdatedCategory.getDeletedAt())
            })
    }

    @Test
    fun given_a_command_with_invalid_id_when_calls_update_category_then_should_return_not_found_exception() {
        val category = Category.newCategory("Film", null, true)

        val expectedName = "Filmes"
        val expectedDescription = "A categoria mais assistida"
        val expectedIsActive = false
        val expectedId = "123"

        val expectedErrorMessage = "Category with ID %s was not found".format(expectedId)
        val expectedErrorCount = 1


        val command = UpdateCategoryCommand.with(
            expectedId,
            expectedName,
            expectedDescription,
            expectedIsActive
        )
        whenever(categoryGateway.findByID(eq(CategoryID.from(expectedId))))
            .thenReturn(null)

        val actualException = Assertions.assertThrows(DomainException::class.java) {
            useCase.execute(command)
        }

        Assertions.assertEquals(expectedErrorCount, actualException.getErrors().size)
        Assertions.assertEquals(expectedErrorMessage, actualException.getErrors()[0].message)

        verify(categoryGateway, times(1)).findByID(eq(CategoryID.from(expectedId)))
        verify(categoryGateway, times(0)).update(any())
    }

}