package com.application;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.controller","com.services"})
@ImportAutoConfiguration(InHouseConfig.class)

public class CarWebApplication {

    public static void main(String[] args) {


        SpringApplication.run(CarWebApplication.class);
    }

    /*@Bean
    public Product product(){
        return new Product();
    }*/
}
