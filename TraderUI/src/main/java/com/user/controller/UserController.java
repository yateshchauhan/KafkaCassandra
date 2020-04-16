package com.trader.controller;


import com.trader.bean.User;
import com.trader.bean.UserResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    private final Logger log = LoggerFactory.getLogger(UserController.class);

    @GetMapping(path = "/user")
    public String isServiceUp(){
        log.info("Service is up and running");
        return "Running";
    }


    @PostMapping(path = "/user/add")
    public UserResponse createUser(@RequestBody User user){

        log.info("UserController::create user service invoked...");

        T

        UserResponse response = new UserResponse(user.getId(), user.getType(),true);

        return response;
    }
}
