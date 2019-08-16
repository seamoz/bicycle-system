package com.ps.allapp.domain;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 消费记录实体类
 * @author VP
 */
@Data
public class Payrecord implements Serializable {

    /**
     * 消费记录ID
     */
    private int id;

    /**
     * 用户ID
     */
    private int userId;

    /**
     * 充值日期
     */
    private Date payTime;

    /**
     * 支付方式
     */
    private String payType;

    /**
     * 支付金额
     */
    private float payMoney;

}