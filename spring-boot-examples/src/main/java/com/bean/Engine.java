package com.bean;


public class Engine {

    private String type;
    private String model;

    public Engine(){
        System.out.println("Engine default constructor called");
    }
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    @Override
    public String toString() {
        return "Engine{" +
                "hashCode='" +hashCode() + "'" +
                "type='" + type + '\'' +
                ", model='" + model + '\'' +
                '}';
    }
}
