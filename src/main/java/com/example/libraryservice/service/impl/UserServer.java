package com.example.libraryservice.service.impl;

import com.example.libraryservice.entity.User;
import com.example.libraryservice.mapper.UserMapper;
import com.example.libraryservice.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServer implements IUserService {
    @Autowired
    UserMapper userMapper;

    @Override
    public List<User> listUsers() {

        return userMapper.listUsers();
    }
}
