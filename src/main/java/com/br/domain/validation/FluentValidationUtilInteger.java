package com.br.domain.validation;

public class FluentValidationUtilInteger {
    public static final FluentValidation <Integer> notNullInteger = FluentValidationImpl.from(s -> s != null);
    public static final FluentValidation <Integer> greaterThanZero = FluentValidationImpl.from(s -> s > 0);
    public static final FluentValidation <Integer> integerMoreThan(int limit) {
        return FluentValidationImpl.from(s -> s > limit);
    };
    public static final FluentValidation <Integer> integerLessThan(int size) {
        return FluentValidationImpl.from(s -> s < size);
    };
    public static final FluentValidation <Integer> integerBetween(int morethan, int lessThan) {
        return integerMoreThan(morethan).and(integerLessThan(lessThan));
    };
}