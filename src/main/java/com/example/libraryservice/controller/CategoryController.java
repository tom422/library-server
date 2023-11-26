package com.example.libraryservice.controller;

import com.example.libraryservice.common.Result;
import com.example.libraryservice.controller.request.AdminPageRequest;
import com.example.libraryservice.entity.Category;
import com.example.libraryservice.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    ICategoryService categoryService;



    @PostMapping("/save")
    public Result save(@RequestBody Category admin){
        categoryService.save(admin);
        return Result.success();
    }



    @GetMapping("/list")
    public Result list(){
        List<Category> users = categoryService.list();
        return Result.success(users);
    }

    @GetMapping("/page")
    public Result page(AdminPageRequest adminPageRequest){
        Object users = categoryService.page(adminPageRequest);
        return Result.success(users);
    }

    @GetMapping("/{id}")
    public Result getById(@PathVariable Integer id) {
        Category category = categoryService.getById(id);
        return Result.success(category);
    }

    @PutMapping("/update")
    public Result update(@RequestBody Category category) {
        categoryService.update(category);
        return  Result.success();
    }

    @GetMapping("/delete/{id}")
    public Result delete(@PathVariable Integer id){
        categoryService.deleteById(id);
        return  Result.success();
    }
}
