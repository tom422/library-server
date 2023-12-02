package com.example.libraryservice.mapper;

import com.example.libraryservice.controller.request.BaseRequest;
import com.example.libraryservice.entity.Category;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CategoryMapper {

//    @Select("select * from user")
    List<Category> list();

    List<Category> listByCondition(BaseRequest baseRequest);

    void save(Category admin);

    Category getById(Integer id);

    void updateById(Category category);

    void deleteById(Integer id);

}
