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
    private Date takeEffectTime;

    /**
     * 失效时间
     */
    private Date loseEffectTime;

    /**
     * 折扣类型
     */
    private Integer discountType;

    /**
     * 失效类型
     */
    private Integer loseEffectType;

    /**
     * 用户ID
     */
    private Integer userId;
}
