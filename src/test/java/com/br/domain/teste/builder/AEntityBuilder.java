package com.br.domain.teste.builder;

import com.br.domain.domain.AEntity;

public interface AEntityBuilder<TEntity extends AEntity> {
    TEntity build();
}