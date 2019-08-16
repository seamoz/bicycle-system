package com.ps.allapp.domain;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 支付记录
 * @author Administrator
 */
@Data
public class PayRecord implements Serializable {
    private Integer id;
    private Integer userId;
    private Date payTime;
    private Integer payType;
    private double payMoney;
}
