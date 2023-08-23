package com.fullcycle.admin.catalogue.application;

import com.fullcycle.admin.catalogue.domain.category.Category;

public abstract class UseCase<IN, OUT> {
    public abstract OUT execute(IN anIN);
}