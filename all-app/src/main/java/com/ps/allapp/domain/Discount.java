package com.ps.allapp.domain;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 优惠券实体类
 */
@Data
public class Discount implements Serializable {

    /**
     * 优惠券ID
     */
    private Integer id;

    /**
     * 折扣金额
     */
    private double discountMoney;

    /**
     * 生效时间
     */
    private String takeEffectTime;

    /**
     * 失效时间
     */
    private String loseEffectTime;

    /**
     * 折扣类型
     */
    private String discountType;

    /**
     * 失效类型
     */
    private String loseEffectType;

    /**
     * 用户ID
     */
    private Integer userId;
}
