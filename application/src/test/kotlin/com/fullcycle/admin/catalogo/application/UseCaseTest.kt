package com.fullcycle.admin.catalogo.application

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class UseCaseTest {
    @Test
    fun test_create_useCase() {
        Assertions.assertNotNull(UseCase())
        Assertions.assertNotNull(UseCase().execute() )
    }
}