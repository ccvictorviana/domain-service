package com.br.domain.teste.repository;

import com.br.domain.domain.Category;
import com.br.domain.domain.Product;
import com.br.domain.repository.interfaces.ICategoryRepository;
import com.br.domain.repository.interfaces.IProductRepository;
import com.br.domain.teste.base.AEntityRepositoryTest;
import com.br.domain.teste.builder.CategoryBuilder;
import com.br.domain.teste.builder.FactoryData;
import com.br.domain.teste.builder.ProductBuilder;
import com.br.domain.teste.config.DomainTestConfig;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
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
public class ProductRepositoryTest extends AEntityRepositoryTest<Product, IProductRepository> {

    @Autowired
    private ICategoryRepository categoryRepo;

    @Test
    @Override
    public void whenInertEntity_withSuccess(){
        Product prod = ProductBuilder.instance().withBasicData().build();
        repository.save(prod);
        Optional<Product> prodDB = repository.findById(prod.getId());
        compareEntity(prodDB.orElseGet(Product::new), prod);
    }

    @Test
    @Override
    public void whenUpdateEntity_withSuccess(){
        Product prod = ProductBuilder.instance().withBasicData().build();
        repository.save(prod);
        Optional<Product> prodDB = repository.findById(prod.getId());
        compareEntity(prodDB.orElseGet(Product::new), prod);

        prod.setName(FactoryData.generateString());
        repository.save(prod);
        prodDB = repository.findById(prod.getId());
        compareEntity(prodDB.orElseGet(Product::new), prod);
    }

    @Test
    @Override
    public void whenDeleteEntity_withSuccess(){
        Product prod = ProductBuilder.instance().withBasicData().build();
        repository.save(prod);
        repository.deleteById(prod.getId());
        Assert.assertTrue(!repository.existsById(prod.getId()));
    }

    @Test
    @Override
    public void whenGetByIdEntity_withSuccess(){
        Product prod = ProductBuilder.instance().withBasicData().build();
        repository.save(prod);
        Optional<Product> prodDB = repository.findById(prod.getId());
        compareEntity(prodDB.orElseGet(Product::new), prod);
    }

    @Test
    @Override
    public void whenListEntity_withSuccess(){
        List<Product> products = new ArrayList<>();
        products.add(ProductBuilder.instance().withBasicData().build());
        products.add(ProductBuilder.instance().withBasicData().build());
        products.add(ProductBuilder.instance().withBasicData().build());
        repository.saveAll(products);

        List<Product> productsDB = repository.findAll();
        Assert.assertEquals(products.size(), productsDB.size());

        products.stream().forEach(prod -> {
            compareEntity(prod, productsDB.stream().filter(prodDB -> prodDB.getId() == prod.getId()).findFirst().get());
        });
    }

    @Test
    public void whenInsertProductWithCategory_whenSuccess(){
        Product prod = ProductBuilder.instance().withBasicData().withCategory(createCategory()).build();
        repository.save(prod);
        Optional<Product> prodDB = repository.findById(prod.getId());
        compareEntity(prodDB.orElseGet(Product::new), prod);
    }


    @Override
    protected void compareEntity(Product entitySource, Product entityTarget) {
        Assert.assertEquals(entitySource.getId(), entityTarget.getId());
        Assert.assertEquals(entitySource.getName(), entityTarget.getName());
        Assert.assertEquals(entitySource.getCreated().compareTo(entityTarget.getCreated()), 0);
        Assert.assertEquals(entitySource.getUpdated().compareTo(entityTarget.getUpdated()), 0);
        Assert.assertTrue(entitySource == null && entityTarget == null ||  entitySource != null && entityTarget != null);

        if(entitySource.getCategory() != null && entityTarget.getCategory() != null)
            Assert.assertEquals(entitySource.getCategory().getId(), entityTarget.getCategory().getId());
    }

    private Category createCategory() {
        Category result = CategoryBuilder.instance().withBasicData().build();
        categoryRepo.save(result);

        return result;
    }
}