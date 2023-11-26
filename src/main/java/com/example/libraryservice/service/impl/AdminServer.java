package com.example.libraryservice.service.impl;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import com.example.libraryservice.controller.dto.LoginDTO;
import com.example.libraryservice.controller.request.AdminPageRequest;
import com.example.libraryservice.controller.request.LoginRequest;
import com.example.libraryservice.controller.request.PasswordRequest;
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
import org.springframework.dao.DuplicateKeyException;
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
            // 设置默认密码 123456
            admin.setPassword(DEFAULT_PASS);
            admin.setPassword(securePass(admin.getPassword()));
        }
        admin.setUpdatetime(date);
        try {
            adminMapper.save(admin);
        } catch (DuplicateKeyException e){
            log.error("插入数据失败，username:{}", admin.getUsername(),e);
            throw new ServiceException("用户名重复");
        }


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
        Admin admin = null;
        try {
            admin = adminMapper.getByUsername(request.getUsername());
        } catch (Exception e){
            log.error("根据用户名{} 查询出错",request.getUsername());
            throw new ServiceException("用户名错误");
        }

        if (admin == null) {
            throw new ServiceException("用户名密码错误！");
        }
        // 判断密码是否合法
        String securePass = securePass(request.getPassword());
        if (!securePass.equals(admin.getPassword())){
            throw new ServiceException("密码错误！");
        }
        if (!admin.isStatus()){
            throw new ServiceException("当前用户处于禁用状态，请联系管理员");
        }
        BeanUtils.copyProperties(admin,loginDTO);
        String token = TokenUtils.genToken(String.valueOf(admin.getId()), admin.getPassword());
        loginDTO.setToken(token);

        return loginDTO;

    }

    @Override
    public void changePass(PasswordRequest request) {
        // 对新的密码进行加密
        request.setNewPass(securePass(request.getNewPass()));
        int count = adminMapper.updatePassword(request);
        if (count <= 0){
            throw new ServiceException("修改密码错误");
        }
    }

    private String securePass(String password) {
        return SecureUtil.md5(password + PASS_SALT);
    }


}
