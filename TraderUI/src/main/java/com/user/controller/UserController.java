package com.user.controller;


import com.user.bean.User;
import com.user.bean.UserResponse;
import com.user.dao.UserDao;
import com.user.exception.UserNotFoundException;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;

@RestController
public class UserController {

    private final Logger log = LoggerFactory.getLogger(UserController.class);

    @GetMapping(path = "/user")
    public String isServiceUp(){
        log.info("Service is up and running");
        return "Running";
    }


    @GetMapping(path="/user/list")
    public void getUser(@RequestParam String name, String id){
        log.info("get user method, name = {}, Id = {}",name,id);
    }

    @PostMapping(path = "/user/add")
    public UserResponse createUser(@Valid @RequestBody User user){

        log.info("UserController::create user service invoked...");

        UserDao userDao = new UserDao();

        user.setCreationDate(LocalDate.now());
        UserResponse response = new UserResponse(user.getId(), user.getType(),userDao.createUser(user));

        return response;
    }

    @GetMapping(path = "/user/{id}")
    public User getUser(@PathVariable String id){
        log.info("Get User service invoked");
        User user = null;
        if(StringUtils.isBlank(id)){
            throw new UserNotFoundException(String.format("Not found user for %s Id",id));
        }
        UserDao dao = new UserDao();
        return isUserExist(id, dao.getUser(id));

    }

    private User isUserExist(String id, User user){
        if(user == null){
            throw new UserNotFoundException(String.format("Not found user for %s Id",id));
        }
        return user;
    }
}
