package com.bean;

import org.springframework.beans.factory.annotation.Autowired;

public class Car {
    private String name;
    private Long price;

    @Autowired
    private Engine engine;

    public Car(){
        System.out.println("Default car constructor");
    }

    public Engine getEngine() {
        return engine;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Car{" +
                "hasCode='"+ hashCode()+ "'" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", engine=" + engine +
                '}';
    }
}
