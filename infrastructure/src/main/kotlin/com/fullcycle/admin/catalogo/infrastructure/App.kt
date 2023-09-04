package com.fullcycle.admin.catalogo.infrastructure

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.core.env.AbstractEnvironment

@SpringBootApplication
open class App {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            App().run(args)
        }
    }

    fun run(args: Array<String>) {
//        SpringApplication.run(WebServerConfig::class.java, *args)
        System.setProperty(AbstractEnvironment.ACTIVE_PROFILES_PROPERTY_NAME, "development")
        runApplication<App>(*args)
    }
}