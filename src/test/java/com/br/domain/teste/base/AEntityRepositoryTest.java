package com.br.domain.teste.base;

import com.br.domain.domain.AEntity;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;

public abstract class AEntityRepositoryTest<TEntity extends AEntity, TEntityRepository extends JpaRepository<TEntity, Long>> {
    @Autowired
    protected TEntityRepository repository;

    @Test
    public abstract void whenInertEntity_withSuccess();

    @Test
    public abstract void whenUpdateEntity_withSuccess();

    @Test
    public abstract void whenDeleteEntity_withSuccess();

    @Test
    public abstract void whenGetByIdEntity_withSuccess();

    @Test
    public abstract void whenListEntity_withSuccess();

    protected abstract void compareEntity(TEntity entitySource, TEntity entityTarget);
}