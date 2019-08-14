package com.ps.allapp.domain;

import lombok.Data;

/**
 * @author VP
 */
@Data
public class Wallet {

    /**
     * 钱包id
     */
    private int id;

    /**
     * 余额
     */
    private double remainMoney;

    /**
     * 免密支付
     */
    private String noPasswordPay;

    /**
     * 是否交押金
     */
    private String  pledgeState;

    /**
     * 支付密码
     */
    private String payPassword;

}
