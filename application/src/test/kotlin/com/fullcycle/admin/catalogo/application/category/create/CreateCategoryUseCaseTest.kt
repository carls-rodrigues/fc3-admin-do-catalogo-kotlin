package com.fullcycle.admin.catalogo.application.category.create

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
class CreateCategoryUseCaseTest {

    @Mock
    private lateinit var categoryGateway: CategoryGateway;

    @InjectMocks
    private lateinit var useCase: DefaultCreateCategoryUseCase;
    @Test
    fun given_an_valid_command_when_call_create_category_then_should_return_category_id() {
        // given
        val expectedName = "Filmes"
        val expectedDescription = "A categoria mais assistida"
        val expectedIsActive = true

        val command = CreateCategoryCommand.with(
            name = expectedName,
            description = expectedDescription,
            isActive = expectedIsActive
        )

        whenever(categoryGateway.create(any()))
            .thenAnswer(returnsFirstArg<CategoryGateway>())

        val actualOutput = useCase.execute(command)

        Assertions.assertNotNull(actualOutput)
        Assertions.assertNotNull(actualOutput.id)

        verify(categoryGateway, times(1))
            .create(argThat { aCategory ->
                Objects.equals(expectedName, aCategory.getName())
                        && Objects.equals(expectedDescription, aCategory.getDescription())
                        && Objects.equals(expectedIsActive, aCategory.isActive())
                        && Objects.nonNull(aCategory.getId())
                        && Objects.nonNull(aCategory.getCreatedAt())
                        && Objects.nonNull(aCategory.getUpdatedAt())
                        && Objects.isNull(aCategory.getDeletedAt())
            })
    }

    @Test
    fun given_an_invalid_name_when_call_create_category_then_should_return_DomainException() {
        val expectedName = "Filmes"
        val expectedDescription = "A categoria mais assistida"
        val expectedIsActive = true

        val command = CreateCategoryCommand.with(
            name = expectedName,
            description = expectedDescription,
            isActive = expectedIsActive
        )

        whenever(categoryGateway.create(any()))
            .thenAnswer(returnsFirstArg<CategoryGateway>())

        val actualOutput = useCase.execute(command)
    }

}
