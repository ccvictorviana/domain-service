package com.br.product.teste.repository;

import com.br.product.domain.Product;
import com.br.product.repository.interfaces.IProductRepository;
import com.br.product.teste.config.DomainTestConfig;
import com.br.product.teste.config.FactoryData;
import com.br.product.teste.repository.base.AEntityRepositoryTest;
import com.br.product.teste.repository.builder.ProductBuilder;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)
@ContextConfiguration(classes = DomainTestConfig.class)
public class ProductRepositoryTest extends AEntityRepositoryTest<Product, IProductRepository> {

    @Test
    @Override
    public void whenInertProduct_withSuccess(){
        Product prod = ProductBuilder.instance().newBasic().build();
        repository.save(prod);
        Optional<Product> prodDB = repository.findById(prod.getId());
        compareEntity(prodDB.orElseGet(Product::new), prod);
    }

    @Test
    @Override
    public void whenUpdateProduct_withSuccess(){
        Product prod = ProductBuilder.instance().newBasic().build();
        repository.save(prod);
        Optional<Product> prodDB = repository.findById(prod.getId());
        compareEntity(prodDB.orElseGet(Product::new), prod);

        prod.setUpdated(new Date());
        prod.setName(FactoryData.generateString(20));
        repository.save(prod);
        prodDB = repository.findById(prod.getId());
        compareEntity(prodDB.orElseGet(Product::new), prod);
    }

    @Test
    @Override
    public void whenDeleteProduct_withSuccess(){
        Product prod = ProductBuilder.instance().newBasic().build();
        repository.save(prod);
        repository.deleteById(prod.getId());
        Assert.assertTrue(!repository.existsById(prod.getId()));
    }

    @Test
    @Override
    public void whenGetByIdProduct_withSuccess(){
        Product prod = ProductBuilder.instance().newBasic().build();
        repository.save(prod);
        Optional<Product> prodDB = repository.findById(prod.getId());
        compareEntity(prodDB.orElseGet(Product::new), prod);
    }

    @Test
    @Override
    public void whenListProduct_withSuccess(){
        List<Product> products = new ArrayList<>();
        products.add(ProductBuilder.instance().newBasic().build());
        products.add(ProductBuilder.instance().newBasic().build());
        products.add(ProductBuilder.instance().newBasic().build());
        repository.saveAll(products);

        List<Product> productsDB = repository.findAll();
        Assert.assertEquals(products.size(), productsDB.size());

        products.stream().forEach(prod -> {
            compareEntity(prod, productsDB.stream().filter(prodDB -> prodDB.getId() == prod.getId()).findFirst().get());
        });
    }

    @Override
    protected void compareEntity(Product entitySource, Product entityTarget) {
        Assert.assertEquals(entitySource.getId(), entityTarget.getId());
        Assert.assertEquals(entitySource.getName(), entityTarget.getName());
        Assert.assertEquals(entitySource.getCreated().compareTo(entityTarget.getCreated()), 0);
        Assert.assertEquals(entitySource.getUpdated().compareTo(entityTarget.getUpdated()), 0);
    }
}