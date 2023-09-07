package com.example.libraryservice.service.impl;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.IdUtil;
import com.example.libraryservice.controller.request.UserPageRequest;
import com.example.libraryservice.entity.User;
import com.example.libraryservice.mapper.UserMapper;
import com.example.libraryservice.service.IUserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class UserServer implements IUserService {
    @Autowired
    UserMapper userMapper;

    @Override
    public List<User> list() {
        return userMapper.list();
    }

    @Override
    public Object page(UserPageRequest userPageRequest) {
        PageHelper.startPage(userPageRequest.getPageNum(),userPageRequest.getPageSize());
        List<User> users = userMapper.listByCondition(userPageRequest);
        return new PageInfo<>(users);
    }

    @Override
    public void save(User user) {
        Date date = new Date();
        // 当作卡号来处理
        user.setUsername(DateUtil.format(date, "yyyyMMdd") + IdUtil.fastSimpleUUID());
        user.setUpdatetime(date);
        userMapper.save(user);
    }


}
