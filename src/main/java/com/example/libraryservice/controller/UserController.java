package com.example.libraryservice.controller;

import com.example.libraryservice.common.Result;
import com.example.libraryservice.controller.request.UserPageRequest;
import com.example.libraryservice.entity.User;

import com.example.libraryservice.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    IUserService userService;

    @PostMapping("/save")
    public Result save(@RequestBody User user){
        userService.save(user);
        return Result.success();
    }

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

    @GetMapping("/{id}")
    public Result getById(@PathVariable Integer id) {
        User user = userService.getById(id);
        return Result.success(user);
    }

    @PostMapping("/update")
    public Result update(@RequestBody User user) {
        userService.update(user);
        return  Result.success();
    }

    @GetMapping("/delete/{id}")
    public Result delete(@PathVariable Integer id){
        userService.deleteById(id);
        return  Result.success();
    }
}
