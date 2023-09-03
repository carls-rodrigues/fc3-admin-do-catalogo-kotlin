package com.fullcycle.admin.catalogo.infrastructure

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
open class Startup
fun main(args: Array<String>) {
    runApplication<Startup>(*args)
}