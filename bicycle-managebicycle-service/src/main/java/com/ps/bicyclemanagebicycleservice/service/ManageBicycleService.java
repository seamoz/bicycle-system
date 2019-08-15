package com.ps.bicyclemanagebicycleservice.service;

import com.ps.allapp.domain.ShareBicycle;
import com.ps.allapp.domain.User;

import java.util.List;

/**
 * @author ZZH
 * @date 2019/8/14 16:50
 */
public interface ManageBicycleService {
    /**
     * 更换地址
     * @param address 地址
     * @return 单车数据
     */
    List<ShareBicycle> changeAddress(String address);

    /**
     * 预约单车
     * @param user 用户对象
     */
    void appointmentBicycle(User user);

    /**
     * 进入骑行首页初始化获取地址和预约车辆
     * @param userId 用户id
     * @return 返回用户预约车辆和地址
     */
    User bicycleInit(int userId);

    /**
     * 校验是否预约单车
     * @param userId
     * @return
     */
    int checkAppointmentBicycle(int userId);

    /**
     * 解锁单车
     * @param user
     */
    void unlockBicycle(User user);

    void cycling(int userId);

    void deduction(int id);

    void malfunction();

    void succeed(String bicycleNum);

    void pay(int userId, float money);


}
