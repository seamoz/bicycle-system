package com.ps.allapp.domain;

import lombok.Data;

import java.io.Serializable;

/**
 * 骑行记录实体类
 */
@Data
public class ShareBicycle implements Serializable {

    /**
     * 骑行记录表
     * */
    private int id ;

    /**
     * 用户id
     * */
    private int userId;

    /**
     * 单车编号
     * */
    private int bicycleNum;

    /**
     * 初始位置
     * */
    private String initialAddress;

    /**
     * 结束位置
     * */
    private String endAddress;

    /**
     * 开始时间
     * */
    private String startTime;

    /**
     * 结束时间
     * */
    private String endTime;

    /**
     * 行车时间
     * */
    private String useTime;

    /**
     * 金额
     * */
    private double money;

    /**
     * 骑行距离
     * */
    private float route;

    /**
     * 骑行状态
     * */
    private int bicycleState;

}
