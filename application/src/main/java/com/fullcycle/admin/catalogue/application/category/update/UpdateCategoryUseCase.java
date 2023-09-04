package com.fullcycle.admin.catalogue.application.category.update;

import com.fullcycle.admin.catalogue.application.UseCase;
import com.fullcycle.admin.catalogue.domain.validation.handler.Notification;
import io.vavr.control.Either;

public abstract class UpdateCategoryUseCase extends UseCase<UpdateCategoryCommand, Either<Notification, UpdateCategoryOutput>> {
}
