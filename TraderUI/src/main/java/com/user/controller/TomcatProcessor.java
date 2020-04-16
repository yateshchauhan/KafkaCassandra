package com.user.controller;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@EnableAutoConfiguration
@ComponentScan(basePackages = {"com.user.controller","com.user.exception"})
public class TomcatProcessor {

    public static void main(String[] args) {
        SpringApplication.run(TomcatProcessor.class);
    }
}
