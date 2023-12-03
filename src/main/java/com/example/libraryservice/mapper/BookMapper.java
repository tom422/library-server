package com.example.libraryservice.mapper;

import com.example.libraryservice.controller.request.BaseRequest;
import com.example.libraryservice.entity.Book;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BookMapper {

//    @Select("select * from user")
    List<Book> list();

    List<Book> listByCondition(BaseRequest baseRequest);

    void save(Book admin);

    Book getById(Integer id);

    void updateById(Book category);

    void deleteById(Integer id);

}
