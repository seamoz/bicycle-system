package com.ps.allapp.domain;

import lombok.Data;

import java.io.Serializable;

/**
 * @author Peigen
 */
@Data
public class UserAndVerify implements Serializable {

    /**
     * 用户id
     */
    private int userId;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 注册时间
     */
    private String registerTime;

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
     * 单车id
     */
    private int bicycleId;

    /**
     * 验证对象
     */
    private Verify verify;

}