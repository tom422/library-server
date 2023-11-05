package com.example.libraryservice.service.impl;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import com.example.libraryservice.controller.dto.LoginDTO;
import com.example.libraryservice.controller.request.AdminPageRequest;
import com.example.libraryservice.controller.request.LoginRequest;
import com.example.libraryservice.controller.request.UserPageRequest;
import com.example.libraryservice.entity.Admin;
import com.example.libraryservice.entity.User;
import com.example.libraryservice.exception.ServiceException;
import com.example.libraryservice.mapper.AdminMapper;
import com.example.libraryservice.mapper.UserMapper;
import com.example.libraryservice.service.IAdminService;
import com.example.libraryservice.service.IUserService;
import com.example.libraryservice.utils.TokenUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Slf4j
@Service
public class AdminServer implements IAdminService {
    @Autowired
    AdminMapper adminMapper;

    private static final String DEFAULT_PASS = "123456";
    private static final String PASS_SALT = "qxl";

    @Override
    public List<Admin> list() {
        return adminMapper.list();
    }

    @Override
    public PageInfo<Admin> page(AdminPageRequest adminPageRequest) {
        PageHelper.startPage(adminPageRequest.getPageNum(),adminPageRequest.getPageSize());
        List<Admin> admins = adminMapper.listByCondition(adminPageRequest);
        return new PageInfo<Admin>(admins);
    }

    @Override
    public void save(Admin admin) {
        Date date = new Date();
        if (StrUtil.isBlank(admin.getPassword())){
            admin.setPassword(DEFAULT_PASS);
            admin.setPassword(securePass(admin.getPassword()));
        }
        admin.setUpdatetime(date);
        adminMapper.save(admin);
    }

    @Override
    public Admin getById(Integer id) {
        return adminMapper.getById(id);
    }

    @Override
    public void update(Admin obj) {
        obj.setUpdatetime(new Date());
        adminMapper.updateById(obj);
    }

    @Override
    public void deleteById(Integer id) {
        adminMapper.deleteById(id);
    }

    @Override
    public LoginDTO login(LoginRequest request) {
        LoginDTO loginDTO = new LoginDTO();
        request.setPassword(securePass(request.getPassword()));
        Admin admin = adminMapper.getByUsernameAndPassword(request);
        if (admin == null) {
            throw new ServiceException("用户名密码错误！");
        }
        BeanUtils.copyProperties(admin,loginDTO);
        String token = TokenUtils.genToken(String.valueOf(admin.getId()), admin.getPassword());
        loginDTO.setToken(token);

        return loginDTO;

    }

    private String securePass(String password) {
        return SecureUtil.md5(password + PASS_SALT);
    }


}
