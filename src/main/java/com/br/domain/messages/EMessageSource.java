package com.br.domain.messages;

public enum EMessageSource {
    VALIDATION_REQUIRED_FIELD_NAME("validation.required.field.name");

    private String key;

    EMessageSource(String key){
        this.key = key;
    }

    public String getKey(){
        return key;
    }
}