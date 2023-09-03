package com.fullcycle.admin.catalogo.application.category.update

import com.fullcycle.admin.catalogo.domain.category.Category
import com.fullcycle.admin.catalogo.domain.category.CategoryGateway
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

}