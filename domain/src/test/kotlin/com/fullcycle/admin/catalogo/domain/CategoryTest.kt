package com.fullcycle.admin.catalogo.domain

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class CategoryTest {
    @Test
    fun test_new_category() {
        Assertions.assertNotNull(Category())
    }
}