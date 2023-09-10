package com.example.libraryservice.service;

import com.example.libraryservice.controller.request.UserPageRequest;
import com.example.libraryservice.entity.User;

import java.util.List;


public interface IUserService {
    List<User> list();

    Object page(UserPageRequest userPageRequest);

    void save(User user);

    User getById(Integer id);

    void update(User user);

    void deleteById(Integer id);
}
