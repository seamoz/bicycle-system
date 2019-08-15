package com.ps.allapp.domain;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @description:
 * @author: peigen
 * @create: 2019/08/15
 */
@Data
public class Verify implements Serializable {

    /**
     * 验证ID
     */
    private int id;

    /**
     * 用户ID
     */
    private int userId;

    /**
     * 发送验证码类型
     */
    private String verifyType;

    /**
     * 验证码
     */
    private String verifyCode;

    /**
     * 发送时间
     */
    private Date verifyTime;

}
