package com.example.libraryservice.service;

import com.example.libraryservice.controller.dto.LoginDTO;
import com.example.libraryservice.controller.request.AdminPageRequest;
import com.example.libraryservice.controller.request.CategoryPageRequest;
import com.example.libraryservice.controller.request.LoginRequest;
import com.example.libraryservice.controller.request.PasswordRequest;
import com.example.libraryservice.entity.Category;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface ICategoryService {
    List<Category> list();

    PageInfo<Category> page(CategoryPageRequest categoryPageRequest);

    void save(Category category);

    Category getById(Integer id);

    void update(Category category);

    void deleteById(Integer id);

     
}
