package com.br.product.teste.repository;

import com.br.product.domain.Product;
import com.br.product.repository.interfaces.IProductRepository;
import com.br.product.teste.config.DomainTestConfig;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.Optional;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = DomainTestConfig.class)
public class ProductRepositoryTest {

    @Autowired
    private IProductRepository repo;

    @Test
    public void whenInertProduct_withSuccess(){
        Product prod = ProductBuilder.instance()
                .withName("Product 1")
                .withCreated(new Date())
                .build();
        repo.save(prod);

        Optional<Product> prodDB = repo.findById(prod.getId());

        compareProduct(prodDB.orElseGet(Product::new), prod);
    }

    public void whenUpdateProduct_withSuccess(){

    }

    public void whenDeleteProduct_withSuccess(){

    }

    public void whenGetByIdProduct_withSuccess(){

    }

    public void whenListProduct_withSuccess(){

    }

    private void compareProduct(Product prod, Product product) {
        Assert.assertEquals(prod.getId(), product.getId());
        Assert.assertEquals(prod.getName(), product.getName());
        Assert.assertEquals(prod.getCreated().compareTo(product.getCreated()), 0);
        Assert.assertEquals(prod.getUpdated().compareTo(product.getUpdated()), 0);
    }
}