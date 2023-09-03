package com.fullcycle.admin.catalogo.application.category.retrieve.list

import com.fullcycle.admin.catalogo.application.category.retrive.list.CategoryListOutput
import com.fullcycle.admin.catalogo.application.category.retrive.list.DefaultListCategoriesUseCase
import com.fullcycle.admin.catalogo.domain.category.Category
import com.fullcycle.admin.catalogo.domain.category.CategoryGateway
import com.fullcycle.admin.catalogo.domain.category.CategorySearchQuery
import com.fullcycle.admin.catalogo.domain.pagination.Pagination
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
class ListCategoriesUseCaseTest {

    @InjectMocks
    private lateinit var useCase: DefaultListCategoriesUseCase

    @Mock
    private lateinit var categoryGateway: CategoryGateway

    @BeforeEach
    fun cleanUp() {
        reset(categoryGateway)
    }

    @Test
    fun `given a valid query when call ListCategories then should return Categories`() {
        val categories = listOf(
            Category.newCategory("Filmes", "", true),
            Category.newCategory("Series", "", true)
        )

        val expectedPage = 0
        val expectedPerPage = 0
        val expectedTerms = ""
        val expectedSort = "createdAt"
        val expectedDirection = "asc"

        val aQuery = CategorySearchQuery(expectedPage, expectedPerPage, expectedTerms, expectedSort, expectedDirection)

        val expectedPagination = Pagination(expectedPage, expectedPerPage, categories.size.toLong(), categories)


        val expectedItemsCount = 2
        val expectedResult = expectedPagination.map(CategoryListOutput::from)

        whenever(categoryGateway.findAll(eq(aQuery)))
            .thenReturn(expectedPagination)

        val actualResult = useCase.execute(aQuery)

        Assertions.assertEquals(expectedItemsCount, actualResult.items.size)
        Assertions.assertEquals(expectedResult, actualResult)
        Assertions.assertEquals(expectedPage, actualResult.currentPage)
        Assertions.assertEquals(expectedPerPage, actualResult.perPage)
        Assertions.assertEquals(categories.size.toLong(), actualResult.total)
    }
}