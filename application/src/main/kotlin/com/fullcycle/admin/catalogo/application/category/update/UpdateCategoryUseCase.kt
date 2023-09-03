package com.fullcycle.admin.catalogo.application.category.update

import com.fullcycle.admin.catalogo.application.UseCase
import com.fullcycle.admin.catalogo.domain.validation.handler.Notification
import io.vavr.control.Either

abstract class UpdateCategoryUseCase : UseCase<UpdateCategoryCommand, Either<Notification, UpdateCategoryOutput>>() {
}