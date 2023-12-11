package com.example.libraryservice.service;

import com.example.libraryservice.controller.request.BaseRequest;
import com.example.libraryservice.entity.Borrow;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface IBorrowService {
    List<Borrow> list();

    PageInfo<Borrow> page(BaseRequest categoryPageRequest);

    void save(Borrow category);

    Borrow getById(Integer id);

    void update(Borrow category);

    void deleteById(Integer id);

     
}
