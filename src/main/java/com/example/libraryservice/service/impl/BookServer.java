package com.example.libraryservice.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.example.libraryservice.controller.request.BaseRequest;
import com.example.libraryservice.controller.request.CategoryPageRequest;
import com.example.libraryservice.entity.Book;
import com.example.libraryservice.mapper.BookMapper;
import com.example.libraryservice.mapper.CategoryMapper;
import com.example.libraryservice.service.IBookService;
import com.example.libraryservice.service.ICategoryService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
@Slf4j
public class BookServer implements IBookService {

    @Resource
    BookMapper bookMapper;

    @Override
    public List<Book> list() {
        return bookMapper.list();
    }

    @Override
    public PageInfo<Book> page(BaseRequest baseRequest) {
        PageHelper.startPage(baseRequest.getPageNum(),baseRequest.getPageSize());
        List<Book> list = bookMapper.listByCondition(baseRequest);
        return new PageInfo<>(list);
    }

    @Override
    public void save(Book obj) {
        obj.setCategory(setCategory(obj.getCategories()));
        bookMapper.save(obj);
    }

    @Override
    public Book getById(Integer id) {
        return bookMapper.getById(id);
    }

    @Override
    public void update(Book obj) {
        obj.setUpdatetime(new Date());
        obj.setCategory(setCategory(obj.getCategories()));
        bookMapper.updateById(obj);
    }

    @Override
    public void deleteById(Integer id) {
        bookMapper.deleteById(id);
    }

    private String setCategory(List<String> categoryes){

        StringBuilder sb = new StringBuilder();
        if (CollUtil.isNotEmpty(categoryes)){
            categoryes.forEach(v-> sb.append(v).append(">"));
            sb.substring(0, sb.lastIndexOf(">"));
        }
        return sb.toString();
    }
}
