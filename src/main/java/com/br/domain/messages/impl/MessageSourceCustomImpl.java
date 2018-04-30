package com.br.domain.messages.impl;

import com.br.domain.messages.EMessageSource;
import com.br.domain.messages.MessageSourceCustom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
public class MessageSourceCustomImpl implements MessageSourceCustom {
    @Autowired
    private MessageSource messageSource;

    @Override
    public String getMessage(EMessageSource eMessageSource){
        return messageSource.getMessage(eMessageSource.getKey(), null, Locale.ENGLISH);
    }
}