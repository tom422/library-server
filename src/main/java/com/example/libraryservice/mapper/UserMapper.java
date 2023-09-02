package com.example.libraryservice.mapper;

import com.example.libraryservice.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
@Mapper
public interface UserMapper {

    @Select("select * from user")
    List<User> listUsers();

}
