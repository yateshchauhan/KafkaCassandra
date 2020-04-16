package com.configuration;

import com.bean.Car;
import com.bean.Engine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CarConfiguration {

    @Autowired
    Car car;
    @Autowired
    Engine engine;

    @Bean
    public Car car(){
        System.out.println("car bean called");
        return new Car();
    }

    @Bean
    public Engine engine(){
        System.out.println("Engine bean called");
        return new Engine();
    }
}
