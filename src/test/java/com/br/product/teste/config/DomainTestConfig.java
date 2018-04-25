package com.br.product.teste.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ComponentScan(basePackages = { "com.br.product" })
@PropertySource("classpath:/application.yml")
public class DomainTestConfig {

}