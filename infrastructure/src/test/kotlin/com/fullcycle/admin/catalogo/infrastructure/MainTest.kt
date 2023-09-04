package com.fullcycle.admin.catalogo.infrastructure

import org.junit.jupiter.api.Test
import org.springframework.core.env.AbstractEnvironment

class MainTest {
    @Test
    fun main_test() {

        System.setProperty(AbstractEnvironment.ACTIVE_PROFILES_PROPERTY_NAME, "test")
        main(arrayOf())
    }
}