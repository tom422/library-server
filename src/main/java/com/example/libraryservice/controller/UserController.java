package com.example.libraryservice.controller;

import com.example.libraryservice.entity.User;

import com.example.libraryservice.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    IUserService userService;

    @GetMapping("/list")
    public List<User> listUsers(){
        return userService.listUsers();
    }
}
