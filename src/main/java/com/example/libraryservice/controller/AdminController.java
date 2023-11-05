package com.example.libraryservice.controller;

import com.example.libraryservice.common.Result;
import com.example.libraryservice.controller.dto.LoginDTO;
import com.example.libraryservice.controller.request.AdminPageRequest;
import com.example.libraryservice.controller.request.LoginRequest;
import com.example.libraryservice.controller.request.UserPageRequest;
import com.example.libraryservice.entity.Admin;
import com.example.libraryservice.service.IAdminService;
import com.example.libraryservice.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    IAdminService adminService;

    @PostMapping("/login")
    public Result login(@RequestBody LoginRequest request){
        LoginDTO loginDTO = adminService.login(request);
        return Result.success(loginDTO);
    }

    @PostMapping("/save")
    public Result save(@RequestBody Admin admin){
        adminService.save(admin);
        return Result.success();
    }

    @GetMapping("/list")
    public Result list(){
        List<Admin> users = adminService.list();
        return Result.success(users);
    }

    @GetMapping("/page")
    public Result page(AdminPageRequest adminPageRequest){
        Object users = adminService.page(adminPageRequest);
        return Result.success(users);
    }

    @GetMapping("/{id}")
    public Result getById(@PathVariable Integer id) {
        Admin admin = adminService.getById(id);
        return Result.success(admin);
    }

    @PostMapping("/update")
    public Result update(@RequestBody Admin admin) {
        adminService.update(admin);
        return  Result.success();
    }

    @GetMapping("/delete/{id}")
    public Result delete(@PathVariable Integer id){
        adminService.deleteById(id);
        return  Result.success();
    }
}
