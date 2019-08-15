package com.ps.allapp.domain;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author VP
 */
@Data
public class Fault implements Serializable {

    /**
     * 故障id
     */
    private int id;

    /**
     * 单车编号
     */
    private String bicycleNum;

    /**
     * 备注
     */
    private String remark;

    /**
     * 故障类型
     */
    private String faultType;

    /**
     * 用户id
     */
    private int userId;

    /**
     * 处理状态
     */
    private String disposeState;

    /**
     * 上报时间
     */
    private Date publishTime;

    /**
     * 处理时间
     */
    private Date disposeTime;
}
