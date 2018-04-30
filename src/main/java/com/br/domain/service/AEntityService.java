package com.br.domain.service;

import com.br.domain.messages.MessageSourceCustom;

public abstract class AEntityService {
    protected MessageSourceCustom messageSource;

    public AEntityService(MessageSourceCustom messageSource){
        this.messageSource = messageSource;
    }
}
