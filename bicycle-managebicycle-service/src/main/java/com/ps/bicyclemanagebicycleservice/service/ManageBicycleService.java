package com.ps.bicyclemanagebicycleservice.service;

import com.ps.allapp.domain.*;

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
     * @param userId
     * @param bicycleNum
     */
    void appointmentBicycle(int userId,int bicycleNum);

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
     * @param userId
     * @param bicycleNum
     */
    void unlockBicycle(int userId,int bicycleNum);

    /**
     * 骑行中
     * @param userId
     * @return
     */
    Result cycling(int userId);

    /**
     * 骑行扣费页
     * @param id    骑行记录ID
     * @return
     */
    Result deduction(int id);

    /**
     * 支付
     * @param userId    用户ID
     * @param money     费用
     * @param payPassword   支付密码
     * @param payType   支付类型
     * @return
     */
    Result pay(int userId, float money, String payPassword, String payType);
    /**
     * 历史故障（用户提交单车的故障）
     * @param userId
     * @return
     */
    Result historyMalfunction(int userId);

    /**
     * 故障的详情资料
     * @param id
     * @return
     */
    Result faultDetails(int id);

    /**
     * 上报故障
     * @param fault
     * @return
     */
    Result sbikeFault(Fault fault);

    /**
     * 关锁
     * @param id      骑行记录ID
     * @param useTime     骑行时间
     * @return
     */
    Result shut(int id, String useTime);

    /**
     * 上报计费异常
     * @param id    骑行记录ID
     * @param abnormal  异常
     * @return
     */
    Result abnormal(int id, String abnormal);

}
