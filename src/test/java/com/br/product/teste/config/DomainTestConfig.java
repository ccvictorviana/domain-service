package com.br.product.teste.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ActiveProfiles;

@Configuration
@ComponentScan(basePackages = { "com.br.product" })
public class DomainTestConfig {

}