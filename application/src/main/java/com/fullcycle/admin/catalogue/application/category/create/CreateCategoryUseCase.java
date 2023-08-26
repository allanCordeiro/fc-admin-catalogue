package com.fullcycle.admin.catalogue.application.category.create;

import com.fullcycle.admin.catalogue.application.UseCase;
import com.fullcycle.admin.catalogue.domain.validation.handler.Notification;
import io.vavr.control.Either;

public abstract class CreateCategoryUseCase
        extends UseCase<CreateCategoryCommand, Either<Notification, CreateCategoryOutput>> {

}
