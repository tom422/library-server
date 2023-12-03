package com.example.libraryservice.service;

import com.example.libraryservice.controller.request.BaseRequest;
import com.example.libraryservice.controller.request.CategoryPageRequest;
import com.example.libraryservice.entity.Book;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface IBookService {
    List<Book> list();

    PageInfo<Book> page(BaseRequest categoryPageRequest);

    void save(Book obj);

    Book getById(Integer id);

    void update(Book obj);

    void deleteById(Integer id);

     
}
