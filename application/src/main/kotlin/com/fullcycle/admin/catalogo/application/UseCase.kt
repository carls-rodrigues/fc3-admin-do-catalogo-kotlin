package com.fullcycle.admin.catalogo.application

abstract class UseCase<IN, OUT> {
    abstract fun execute(command: IN): OUT

}