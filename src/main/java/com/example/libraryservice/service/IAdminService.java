package com.example.libraryservice.service;

import com.example.libraryservice.controller.dto.LoginDTO;
import com.example.libraryservice.controller.request.AdminPageRequest;
import com.example.libraryservice.controller.request.LoginRequest;
import com.example.libraryservice.controller.request.PasswordRequest;
import com.example.libraryservice.controller.request.UserPageRequest;
import com.example.libraryservice.entity.Admin;
import com.example.libraryservice.entity.User;
import com.github.pagehelper.PageInfo;

import java.util.List;


public interface IAdminService {
    List<Admin> list();

    PageInfo<Admin> page(AdminPageRequest adminPageRequest);

    void save(Admin admin);
  
    Admin getById(Integer id);

    void update(Admin admin);

    void deleteById(Integer id);

    LoginDTO login(LoginRequest request);

    void changePass(PasswordRequest request);
}
