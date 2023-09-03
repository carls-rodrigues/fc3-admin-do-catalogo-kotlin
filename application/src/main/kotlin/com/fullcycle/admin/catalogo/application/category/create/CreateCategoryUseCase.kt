package com.fullcycle.admin.catalogo.application.category.create

import com.fullcycle.admin.catalogo.application.UseCase
import com.fullcycle.admin.catalogo.domain.validation.handler.Notification
import io.vavr.control.Either

abstract class CreateCategoryUseCase : UseCase<CreateCategoryCommand, Either<Notification, CreateCategoryOutput>>()