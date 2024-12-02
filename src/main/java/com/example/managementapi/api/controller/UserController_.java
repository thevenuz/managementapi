package com.example.managementapi.api.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController_ {

    @GetMapping("/user")
    public String getUser(){
        return "Hello World";
    }

}
