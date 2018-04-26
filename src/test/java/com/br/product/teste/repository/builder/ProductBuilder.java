package com.br.product.teste.repository.builder;

import com.br.product.domain.Product;
import com.br.product.teste.config.FactoryData;

import java.util.Date;

public class ProductBuilder {

    private Long id;
    private String name;
    private Date created;
    private Date updated;

    public static ProductBuilder instance(){
        return new ProductBuilder();
    }

    public ProductBuilder withId(Long id){
        this.id = id;
        return this;
    }

    public ProductBuilder withName(String name){
        this.name = name;
        return this;
    }

    public ProductBuilder withCreated(Date created){
        this.created = created;
        return this;
    }

    public ProductBuilder withUpdated(Date updated){
        this.updated = updated;
        return this;
    }

    public ProductBuilder newBasic(){
        withName("Product " + FactoryData.generateString(10));
        withCreated(new Date());
        return this;
    }

    public Product build(){
        Product result = new Product();
        result.setCreated(created);
        result.setName(name);
        result.setCreated(created);
        result.setUpdated(updated);

        return result;
    }
}