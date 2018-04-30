package com.br.domain.validation;

public class FluentValidationUtilString {
    public static final FluentValidation <String> notNull = FluentValidationImpl.from(s -> s != null);
    public static final FluentValidation <String> notEmpty = FluentValidationImpl.from(s -> !s.isEmpty());
    public static final FluentValidation <String> sizeMoreThan(int size) {
        return FluentValidationImpl.from(s -> s.length() > size);
    };
    public static final FluentValidation <String> sizeLessThan(int size) {
        return FluentValidationImpl.from(s -> s.length() < size);
    }
    public static final FluentValidation <String> sizeBetween(int morethan, int lessThan) {
        return sizeMoreThan(morethan).and(sizeLessThan(lessThan));
    };
    public static final FluentValidation <String> notNullAndEmpty() {
        return notNull.and(notEmpty);
    };
}