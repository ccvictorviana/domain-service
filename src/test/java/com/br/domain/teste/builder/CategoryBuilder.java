package com.br.domain.teste.builder;

import com.br.domain.domain.Category;

public class CategoryBuilder implements AEntityBuilder<Category>{
    private String name;

    public static CategoryBuilder instance(){
        return new CategoryBuilder();
    }

    public CategoryBuilder withName(String name){
        this.name = name;
        return this;
    }

    public CategoryBuilder withBasicData(){
        withName("Category " + FactoryData.generateString(10));
        return this;
    }

    @Override
    public Category build() {
        Category result = new Category();
        result.setName(name);
        return result;
    }
}