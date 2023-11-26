package com.example.libraryservice.service.impl;

import com.example.libraryservice.controller.request.AdminPageRequest;
import com.example.libraryservice.entity.Category;
import com.example.libraryservice.mapper.CategoryMapper;
import com.example.libraryservice.service.ICategoryService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
@Slf4j
public class CategoryServer implements ICategoryService {

    @Resource
    CategoryMapper categoryMapper;

    @Override
    public List<Category> list() {
        return categoryMapper.list();
    }

    @Override
    public PageInfo<Category> page(AdminPageRequest adminPageRequest) {
        PageHelper.startPage(adminPageRequest.getPageNum(),adminPageRequest.getPageSize());
        List<Category> categories = categoryMapper.listByCondition(adminPageRequest);
        return new PageInfo<Category>(categories);
    }

    @Override
    public void save(Category category) {
        categoryMapper.save(category);
    }

    @Override
    public Category getById(Integer id) {
        return categoryMapper.getById(id);
    }

    @Override
    public void update(Category category) {
        categoryMapper.updateById(category);
    }

    @Override
    public void deleteById(Integer id) {
        categoryMapper.deleteById(id);
    }
}
