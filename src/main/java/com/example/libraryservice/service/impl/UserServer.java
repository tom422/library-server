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
    public PageInfo<User> page(UserPageRequest userPageRequest) {
        PageHelper.startPage(userPageRequest.getPageNum(),userPageRequest.getPageSize());
        List<User> users = userMapper.listByCondition(userPageRequest);
        return new PageInfo<>(users);
    }

    @Override
    public void save(User user) {
        Date date = new Date();
        // 当作卡号来处理
        user.setUsername(DateUtil.format(date, "yyyyMMdd") + Math.abs(IdUtil.fastSimpleUUID().hashCode()));
        user.setUpdatetime(date);
        userMapper.save(user);
    }

    @Override
    public User getById(Integer id) {
        return userMapper.getById(id);
    }

    @Override
    public void update(User user) {
        user.setUpdatetime(new Date());
        userMapper.updateById(user);
    }

    @Override
    public void deleteById(Integer id) {
        userMapper.deleteById(id);
    }

    @Override
    public void handleAccount(User user) {
        Integer score = user.getScore();
        if (score == null){
            return;
        }
        Integer id = user.getId();
        User dbUser = userMapper.getById(id);
        dbUser.setAccount(dbUser.getAccount() + score);
        userMapper.updateById(dbUser);
    }


}
