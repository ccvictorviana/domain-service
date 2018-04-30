package com.br.domain.validation;

import com.br.domain.exceptions.DomainBusinessException;

import java.util.Optional;

public class FluentValidationResult {
    private boolean valid;

    public boolean isValid() {
        return valid;
    }

    private FluentValidationResult(boolean valid) {
        this.valid = valid;
    }

    public static FluentValidationResult ok() {
        return new FluentValidationResult(true);
    }

    public static FluentValidationResult fail() {
        return new FluentValidationResult(false);
    }

    public Optional< String > getFieldNameIfInvalid(String field) {
        return this.valid ? Optional.empty() : Optional.of(field);
    }

    public void ifInvalidThrows(String message){
        throw new DomainBusinessException(message);
    }
}