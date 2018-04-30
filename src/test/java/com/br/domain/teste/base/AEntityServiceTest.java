package com.br.domain.teste.base;

import com.br.domain.messages.MessageSourceCustom;
import org.junit.Before;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class AEntityServiceTest {
    @Autowired
    protected MessageSourceCustom messages;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }
}
