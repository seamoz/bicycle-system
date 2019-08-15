package com.ps.allapp.domain;

import lombok.Data;

import java.io.Serializable;

@Data
public class User implements Serializable {

    /**
     * 用户id
     * */
    private Integer id;

    /**
     * 用户名
     * */
    private String username;

    /**
     * 密码
     * */
    private String password;

    /**
     * 手机号
     * */
    private String phone;

    /**
     * 邮箱
     * */
    private String email;

    /**
     * 注册时间
     * */
    private String registerTime;

    /**
     * 里程
     * */
    private double route;

    /**
     * 头像
     * */
    private String headPhoto;

    /**
     * 地址
     * */
    private String address;

    /**
     * 钱包id
     * */
    private Integer walletId;

    /**
     * 单车id
     * */
    private Integer bicycleId;

    /**
     * 单车编号
     */
    private int bicycleNum;
}