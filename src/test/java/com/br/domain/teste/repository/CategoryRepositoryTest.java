package com.br.domain.teste.repository;

import com.br.domain.domain.Category;
import com.br.domain.repository.interfaces.ICategoryRepository;
import com.br.domain.teste.base.AEntityRepositoryTest;
import com.br.domain.teste.builder.CategoryBuilder;
import com.br.domain.teste.builder.FactoryData;
import com.br.domain.teste.config.DomainTestConfig;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)
@ContextConfiguration(classes = DomainTestConfig.class)
public class CategoryRepositoryTest extends AEntityRepositoryTest<Category, ICategoryRepository> {

    @Test
    @Override
    public void whenInertEntity_withSuccess(){
        Category category = CategoryBuilder.instance().withBasicData().build();
        repository.save(category);
        Optional<Category> categoryDB = repository.findById(category.getId());
        compareEntity(categoryDB.orElseGet(Category::new), category);
    }

    @Test
    @Override
    public void whenUpdateEntity_withSuccess(){
        Category category = CategoryBuilder.instance().withBasicData().build();
        repository.save(category);
        Optional<Category> categoryDB = repository.findById(category.getId());
        compareEntity(categoryDB.orElseGet(Category::new), category);

        category.setName(FactoryData.generateString());
        repository.save(category);
        categoryDB = repository.findById(category.getId());
        compareEntity(categoryDB.orElseGet(Category::new), category);
    }

    @Test
    @Override
    public void whenDeleteEntity_withSuccess(){
        Category prod = CategoryBuilder.instance().withBasicData().build();
        repository.save(prod);
        repository.deleteById(prod.getId());
        Assert.assertTrue(!repository.existsById(prod.getId()));
    }

    @Test
    @Override
    public void whenGetByIdEntity_withSuccess(){
        Category prod = CategoryBuilder.instance().withBasicData().build();
        repository.save(prod);
        Optional<Category> prodDB = repository.findById(prod.getId());
        compareEntity(prodDB.orElseGet(Category::new), prod);
    }

    @Test
    @Override
    public void whenListEntity_withSuccess(){
        List<Category> categories = new ArrayList<>();
        categories.add(CategoryBuilder.instance().withBasicData().build());
        categories.add(CategoryBuilder.instance().withBasicData().build());
        categories.add(CategoryBuilder.instance().withBasicData().build());
        repository.saveAll(categories);

        List<Category> categoriesDB = repository.findAll();
        Assert.assertEquals(categories.size(), categoriesDB.size());

        categories.stream().forEach(prod -> {
            compareEntity(prod, categoriesDB.stream().filter(prodDB -> prodDB.getId() == prod.getId()).findFirst().get());
        });
    }

    @Override
    protected void compareEntity(Category entitySource, Category entityTarget) {
        Assert.assertEquals(entitySource.getId(), entityTarget.getId());
        Assert.assertEquals(entitySource.getName(), entityTarget.getName());
        Assert.assertEquals(entitySource.getCreated().compareTo(entityTarget.getCreated()), 0);
        Assert.assertEquals(entitySource.getUpdated().compareTo(entityTarget.getUpdated()), 0);
    }
}