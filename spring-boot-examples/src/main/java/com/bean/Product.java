package com.bean;

import org.springframework.hateoas.RepresentationModel;


public class Product  extends RepresentationModel<Product>{

    private String name;

    public Product(){
        System.out.println("product constructor invoked");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                "hashCode='" + hashCode() + '\'' +
                '}';
    }
}
