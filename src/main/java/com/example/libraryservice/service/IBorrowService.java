package com.example.libraryservice.service;

import com.example.libraryservice.controller.request.BaseRequest;
import com.example.libraryservice.entity.Book;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface IBorrowService {
    List<Book> list();

    PageInfo<Book> page(BaseRequest categoryPageRequest);

    void save(Book category);

    Book getById(Integer id);

    void update(Book category);

    void deleteById(Integer id);

     
}
