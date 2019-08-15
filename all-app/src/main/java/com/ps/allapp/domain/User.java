package com.ps.allapp.domain;

import lombok.Data;

import java.io.Serializable;

@Data
public class User implements Serializable {

    private Integer Id;//用户id
    private String username;//用户名
    private String password;//密码
    private String phone;//手机号
    private String email;//邮箱
    private String registerTime;//注册时间
    private double route;//里程
    private String headPhoto;//头像
    private String address;//地址
    private Integer walletId;//钱包id
    private Integer bicycleId;//单车id
}