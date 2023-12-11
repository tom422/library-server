package com.example.libraryservice.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.example.libraryservice.controller.request.BaseRequest;
import com.example.libraryservice.entity.Book;
import com.example.libraryservice.entity.Borrow;
import com.example.libraryservice.entity.User;
import com.example.libraryservice.exception.ServiceException;
import com.example.libraryservice.mapper.BookMapper;
import com.example.libraryservice.mapper.BorrowMapper;
import com.example.libraryservice.mapper.UserMapper;
import com.example.libraryservice.service.IBookService;
import com.example.libraryservice.service.IBorrowService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Service
@Slf4j
public class BorrowServer implements IBorrowService {

    @Resource
    BorrowMapper borrowMapper;

    @Autowired
    UserMapper userMapper;

    @Autowired
    BookMapper bookMapper;

    @Override
    public List<Borrow> list() {
        return borrowMapper.list();
    }

    @Override
    public PageInfo<Borrow> page(BaseRequest baseRequest) {
        PageHelper.startPage(baseRequest.getPageNum(),baseRequest.getPageSize());
        List<Borrow> borrows = borrowMapper.listByCondition(baseRequest);

        for (Borrow borrow : borrows){
            Date returnDate = borrow.getReturnDate();
            Date now = new Date();

            if (isSameDay(dateAddDays(now,1), returnDate)){
                borrow.setNote("即将到期");
            } else if (isSameDay(now, returnDate)){
                borrow.setNote("已到期");
            } else if (now.compareTo(returnDate) == 1){
                borrow.setNote("已过期");
            } else {
                borrow.setNote("正常");
            }
        }
        return new PageInfo<>(borrows);
    }

    @Override
    @Transactional
    public void save(Borrow obj) {
        // 1. 校验用户积分是否足够
        String usreNo = obj.getUserNo();
        User user = userMapper.getByUsername(usreNo);
        if (Objects.isNull(user)){
            throw new ServiceException("-1","用户不存在");
        }
        // 2. 校验图书数量是否足够
        Book book = bookMapper.getByNo(obj.getBookNo());
        if (Objects.isNull(book)){
            throw new ServiceException("-1","图书不存在");
        }
        // 校验图书数量
        if (book.getNums() < 1){
            throw new ServiceException("图书数量不足");
        }
        Integer account = user.getAccount();
        Integer score = book.getScore() * obj.getDays();
        // 验证用户账户余额
        if (score > account){
            throw new ServiceException("用户积分不足");
        }

        // 更新用户余额
        user.setAccount(user.getAccount() - score);
        userMapper.updateById(user);
        // 更新图书的数量
        book.setNums(book.getNums()-1);
        bookMapper.updateById(book);

        // 日期加天数
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.add(Calendar.DATE, obj.getDays()); //minus number would decrement the days
        Date date = cal.getTime();
        obj.setReturnDate(date);
        // 新增借书记录
        borrowMapper.save(obj);
    }

    @Override
    public Borrow getById(Integer id) {
        return borrowMapper.getById(id);
    }

    @Override
    public void update(Borrow obj) {
        obj.setUpdatetime(new Date());
        borrowMapper.updateById(obj);
    }

    @Override
    public void deleteById(Integer id) {
        borrowMapper.deleteById(id);
    }

    private boolean isSameDay(Date date1, Date date2) {
        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(date1);

        Calendar cal2 = Calendar.getInstance();
        cal2.setTime(date2);

        return cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR)
                && cal1.get(Calendar.MONTH) == cal2.get(Calendar.MONTH)
                && cal1.get(Calendar.DAY_OF_MONTH) == cal2.get(Calendar.DAY_OF_MONTH);
    }

    private Date dateAddDays(Date date, int days){
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, days );
        return  cal.getTime();
    }

}
