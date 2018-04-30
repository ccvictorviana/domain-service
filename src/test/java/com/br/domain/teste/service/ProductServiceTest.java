package com.br.domain.teste.service;

import com.br.domain.domain.Product;
import com.br.domain.exceptions.DomainBusinessException;
import com.br.domain.messages.EMessageSource;
import com.br.domain.messages.MessageSourceCustom;
import com.br.domain.repository.interfaces.ProductRepository;
import com.br.domain.service.ProductService;
import com.br.domain.service.impl.ProductServiceImpl;
import com.br.domain.teste.base.AEntityServiceTest;
import com.br.domain.teste.builder.ProductBuilder;
import com.br.domain.teste.config.ServiceTestConfig;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = ServiceTestConfig.class)
public class ProductServiceTest extends AEntityServiceTest {
    @Autowired
    private MessageSourceCustom messageSource;
    @Mock
    private ProductRepository productRepo;

    private ProductService productService;

    @Before
    public void setup(){
        productService = new ProductServiceImpl(this.messageSource);
    }

    @Test
    public void whenInsertProductWithoutName_thenThrowsException(){
        Product product = ProductBuilder.instance().build();

        try {
            productService.save(product);
            fail("Validation not work.");
        }catch(DomainBusinessException ex){
            verify(productRepo, never()).save(any());
            Assert.assertEquals(ex.getMessage(), messages.getMessage(EMessageSource.VALIDATION_REQUIRED_FIELD_NAME));
        }
    }
}
