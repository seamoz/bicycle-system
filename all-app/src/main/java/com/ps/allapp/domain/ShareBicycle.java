package com.ps.allapp.domain;

import lombok.Data;

import java.io.Serializable;

/**
 * 骑行记录实体类
 */
@Data
public class ShareBicycle implements Serializable {

    private int id ;//骑行记录表

    private int userId;//用户id

    private int bicycleNum;//单车编号

    private String initialAddress;//初始位置

    private String endAddress;//结束位置

    private String startTime;//开始时间

    private String endTime;//结束时间

    private String useTime;//行车时间

    private double money;//金额

    private float route;//骑行距离

    private int bicycleState;//骑行状态
}
