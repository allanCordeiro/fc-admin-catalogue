package com.fullcycle.admin.catalogue.application.category.retrieve.get;

import com.fullcycle.admin.catalogue.domain.category.CategoryGateway;
import com.fullcycle.admin.catalogue.domain.category.CategoryID;
import com.fullcycle.admin.catalogue.domain.exceptions.DomainException;
import com.fullcycle.admin.catalogue.domain.validation.Error;

import java.util.Objects;
import java.util.function.Supplier;


public class DefaultGetCategoryByIdUseCase extends GetCategoryByIdUseCase {
    private final CategoryGateway categoryGateway;

    public DefaultGetCategoryByIdUseCase(final CategoryGateway categoryGateway) {
        this.categoryGateway = Objects.requireNonNull(categoryGateway);
    }

    @Override
    public CategoryOutput execute(final String anId) {
        final var aCategoryId = CategoryID.from(anId);
        return this.categoryGateway.findById(aCategoryId)
                .map(CategoryOutput::from)
                .orElseThrow(notFound(aCategoryId));
    }

    private Supplier<DomainException> notFound(final CategoryID anId) {
        return () -> DomainException.with(
                new Error("Category with ID %s was not found".formatted(anId.getValue()))
        );
    }
}