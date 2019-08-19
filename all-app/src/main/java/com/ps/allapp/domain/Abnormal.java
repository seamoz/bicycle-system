package com.ps.allapp.domain;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 上报计费异常实体类
 */
@Data
public class Abnormal implements Serializable {

    /**
     * 上报计费异常ID
     */
    private Integer id;

    /**
     * 用户ID
     */
    private Integer userId;

    /**
     * 骑行记录ID
     */
    private Integer shareId;

    /**
     * 异常
     */
    private String abnormal;

    /**
     * 上报时间
     */
    private Date reportTime;

}
