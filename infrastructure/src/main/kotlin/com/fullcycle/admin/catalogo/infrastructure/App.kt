package com.fullcycle.admin.catalogo.infrastructure

import com.fullcycle.admin.catalogo.infrastructure.configuration.WebServerConfig
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
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
        System.setProperty(AbstractEnvironment.ACTIVE_PROFILES_PROPERTY_NAME, "development")
        SpringApplication.run(WebServerConfig::class.java, *args)
//        runApplication<App>(*args)
    }
}