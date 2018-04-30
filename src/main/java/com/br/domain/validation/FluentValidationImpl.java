package com.br.domain.validation;

import java.util.function.Predicate;

public class FluentValidationImpl<K> implements FluentValidation <K> {
    private Predicate<K> predicate;

    public static <K> FluentValidationImpl<K> from(Predicate <K> predicate) {
        return new FluentValidationImpl<>(predicate);
    }

    private FluentValidationImpl(Predicate <K> predicate) {
        this.predicate = predicate;
    }

    @Override
    public FluentValidationResult test(K param) {
        return predicate.test(param) ? FluentValidationResult.ok() : FluentValidationResult.fail();
    }
}