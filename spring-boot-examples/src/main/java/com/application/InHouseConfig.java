package com.application;

import com.bean.Product;
import com.services.ProductCaringService;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@EnableAutoConfiguration
@Configuration
public class InHouseConfig {

    @Bean
    public Product product(){
        return new Product();
    }

    @Bean
    public ProductCaringService productCaringService(){
        return new ProductCaringService();
    }
}
