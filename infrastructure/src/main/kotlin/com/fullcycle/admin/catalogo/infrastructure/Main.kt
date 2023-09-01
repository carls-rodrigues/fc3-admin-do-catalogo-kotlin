package com.fullcycle.admin.catalogo.infrastructure

import com.fullcycle.admin.catalogo.application.UseCase

fun main(args: Array<String>) {
    println("Starting application")
    println(UseCase().execute())
}