package com.example.libraryservice.controller;

import cn.hutool.core.collection.CollUtil;
import com.example.libraryservice.common.Result;
import com.example.libraryservice.controller.request.AdminPageRequest;
import com.example.libraryservice.controller.request.CategoryPageRequest;
import com.example.libraryservice.entity.Category;
import com.example.libraryservice.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    ICategoryService categoryService;



    @PostMapping("/save")
    public Result save(@RequestBody Category category){
        categoryService.save(category);
        return Result.success();
    }



    @GetMapping("/list")
    public Result list(){
        List<Category> list = categoryService.list();
        return Result.success(list);
    }

    @GetMapping("/tree")
    public Result tree(){
        List<Category> list = categoryService.list();

        return Result.success(createTrre(null,list));
    }

    private List<Category> createTrre(Integer pid,List<Category> categories){
        List<Category> tree = new ArrayList<>();
        for (Category category : categories){
            if (pid == null){
                if (category.getPid() == null){
                    tree.add(category);
                    category.setChildren(createTrre(category.getId(),categories));
                }
            }else {
                if (pid.equals(category.getPid())){
                    tree.add(category);
                    category.setChildren(createTrre(category.getId(),categories));
                }
            }

            if (CollUtil.isEmpty(category.getChildren())){
                category.setChildren(null);
            }
        }

        return tree;
    }

    @GetMapping("/page")
    public Result page(CategoryPageRequest categoryPageRequest){
        Object categorys = categoryService.page(categoryPageRequest);
        return Result.success(categorys);
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
