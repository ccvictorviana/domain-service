package com.br.product.teste.repository.base;

import com.br.product.domain.AEntity;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;

public abstract class AEntityRepositoryTest<TEntity extends AEntity, TEntityRepository extends JpaRepository<TEntity, Long>> {
    @Autowired
    protected TEntityRepository repository;

    @Test
    public abstract void whenInertProduct_withSuccess();

    @Test
    public abstract void whenUpdateProduct_withSuccess();

    @Test
    public abstract void whenDeleteProduct_withSuccess();

    @Test
    public abstract void whenGetByIdProduct_withSuccess();

    @Test
    public abstract void whenListProduct_withSuccess();

    protected abstract void compareEntity(TEntity entitySource, TEntity entityTarget);
}