package com.example.libraryservice.mapper;

import com.example.libraryservice.controller.request.BaseRequest;
import com.example.libraryservice.controller.request.LoginRequest;
import com.example.libraryservice.controller.request.PasswordRequest;
import com.example.libraryservice.controller.request.UserPageRequest;
import com.example.libraryservice.entity.Admin;
import com.example.libraryservice.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface AdminMapper {

//    @Select("select * from user")
    List<Admin> list();

    List<Admin> listByCondition(BaseRequest baseRequest);

    void save(Admin admin);

    Admin getById(Integer id);

    void updateById(Admin admin);

    void deleteById(Integer id);

    Admin getByUsernameAndPassword(@Param("username") String username, @Param("password") String password);

    int updatePassword(PasswordRequest passwordRequest);

    Admin getByUsername(String username);
}
