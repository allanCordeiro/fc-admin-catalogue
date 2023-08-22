package com.fullcycle.admin.catalogue.domain;

import com.fullcycle.admin.catalogue.domain.validation.ValidationHandler;

public abstract  class AggregateRoot<ID extends Identifier> extends Entity<ID> {
    public AggregateRoot(final ID id) {
        super(id);
    }
}
