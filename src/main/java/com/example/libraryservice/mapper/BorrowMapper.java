package com.example.libraryservice.mapper;

import com.example.libraryservice.controller.request.BaseRequest;
import com.example.libraryservice.entity.Borrow;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BorrowMapper {
    List<Borrow> list();

    List<Borrow> listByCondition(BaseRequest baseRequest);

    void save(Borrow admin);

    Borrow getById(Integer id);

    void updateById(Borrow category);

    void deleteById(Integer id);
}
