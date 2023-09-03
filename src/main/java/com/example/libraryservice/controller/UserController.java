package com.example.libraryservice.controller;

import com.example.libraryservice.common.Result;
import com.example.libraryservice.controller.request.UserPageRequest;
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
    public Result list(){
        List<User> users = userService.list();
        return Result.success(users);
    }

    @GetMapping("/page")
    public Result page(UserPageRequest userPageRequest){
        Object users = userService.page(userPageRequest);
        return Result.success(users);
    }
}
