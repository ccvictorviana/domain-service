package com.br.domain.validation;

@FunctionalInterface
public interface FluentValidation <K> {
    FluentValidationResult test(K param);

    default FluentValidation <K> and(FluentValidation <K> other) {
        return (param) -> {
            FluentValidationResult result = this.test(param);
            return !result.isValid() ? result : other.test(param);
        };
    }

    default FluentValidation <K> or(FluentValidation <K> other) {
        return (param) -> {
            FluentValidationResult result = this.test(param);
            return result.isValid() ? result : other.test(param);
        };
    }
}