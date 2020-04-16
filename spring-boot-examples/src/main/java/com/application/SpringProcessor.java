package com.application;


import com.bean.Car;
import com.configuration.CarConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

//@EnableAutoConfiguration
public class SpringProcessor {

    public static void main1(String[] args) {
        SpringApplication.run(SpringProcessor.class);


    }

    public static void main(String[] args) {

        System.out.println("spring processor invoked");
        //SpringApplication.run(SpringProcessor.class);

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(CarConfiguration.class);
        context.refresh();

        Car car1 = (Car)context.getBean("car");
        Car car2 = (Car)context.getBean("car");

        System.out.println(car1);
        System.out.println(car2);

        System.out.println("configuration loaded, plz validate further....");


        context.close();

    }
}
