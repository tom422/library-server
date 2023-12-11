package com.example.libraryservice.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class Borrow   {


    /**
     * id
     */
    private Integer id;

    /**
     * 图书名称
     */
    private String bookName;

    /**
     * 图书标准码
     */
    private String bookNo;

    /**
     * 会员码
     */
    private String userNo;

    /**
     * 用户名称
     */
    private String userName;

    /**
     * 用户联系方式
     */
    private String userPhone;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss", timezone = "GMT+8")
    private Date createtime;

    /**
     * 更新时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss", timezone = "GMT+8")
    private Date updatetime;

    /**
     * 借书积分
     */
    private Integer score;
    private String status;
    private Integer days;
    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss", timezone = "GMT+8")
    private Date returnDate;
    // 提醒状态 即将到期 (-1) 已到期（当天） 已过期（超过归还日期之后之后）
    private String note;


}
