package com.fullcycle.admin.catalogue.application.category.retrieve.list;

import com.fullcycle.admin.catalogue.application.UseCase;
import com.fullcycle.admin.catalogue.domain.category.CategorySearchQuery;
import com.fullcycle.admin.catalogue.domain.pagination.Pagination;

public abstract class ListCategoriesUseCase extends UseCase<CategorySearchQuery, Pagination<CategoryListOutput>> {
}
