package com.br.domain.service.impl;

import com.br.domain.domain.Product;
import com.br.domain.messages.EMessageSource;
import com.br.domain.messages.MessageSourceCustom;
import com.br.domain.service.AEntityService;
import com.br.domain.service.ProductService;
import com.br.domain.validation.FluentValidationUtilString;
import org.springframework.stereotype.Component;

import javax.inject.Inject;

@Component
public class ProductServiceImpl extends AEntityService implements ProductService {

    @Inject
    public ProductServiceImpl(MessageSourceCustom messageSource){
        super(messageSource);
    }

    @Override
    public void save(Product product) {
        FluentValidationUtilString.notNullAndEmpty().test(product.getName())
                .ifInvalidThrows(messageSource.getMessage(EMessageSource.VALIDATION_REQUIRED_FIELD_NAME));
    }
}
