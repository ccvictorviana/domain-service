package com.br.domain.teste.builder;

import com.br.domain.domain.Category;
import com.br.domain.domain.Product;

public class ProductBuilder implements AEntityBuilder{
    private String name;
    private Category category;

    public static ProductBuilder instance(){
        return new ProductBuilder();
    }

    public ProductBuilder withName(String name){
        this.name = name;
        return this;
    }

    public ProductBuilder withCategory(Category category){
        this.category = category;
        return this;
    }

    public ProductBuilder withBasicData(){
        withName("Product " + FactoryData.generateString(10));
        return this;
    }

    public Product build(){
        Product result = new Product();
        result.setName(name);
        result.setCategory(category);
        return result;
    }
}