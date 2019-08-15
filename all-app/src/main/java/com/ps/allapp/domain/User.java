package com.ps.allapp.domain;

import lombok.Data;

import java.io.Serializable;

@Data
public class User implements Serializable {

    private int userId;//用户id

    private String username;//用户名

    private String password;//密码

    private String phone;//手机号

    private String email;//邮箱

    private String registerTime;//注册时间
    /**
     * 里程
     */
    private double route;
    /**
     * 头像
     */
    private String headPhoto;
    /**
     * 地址
     */
    private String address;
    /**
     * 钱包id
     */
    private int walletId;
    /**
     * 单车编号
     */
    private int bicycleNum;
}