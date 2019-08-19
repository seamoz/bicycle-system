package com.ps.bicyclemanagebicycleservice.mapper;

import com.ps.allapp.domain.*;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author ZZH
 */
@Mapper
@Repository
public interface ManageBicycleMapper {
    /**
     * 获取当前地址的车辆
     * @param address
     * @return
     */
    List<ShareBicycle> changeAddress(String address);

    /**
     * 首页初始化
     * @param userId
     * @return
     */
    User bicycleInit(int userId);

    /**
     * 校验是否预约单车
     * @param userId
     * @return
     */
    User checkAppointment(int userId);

    /**
     * 预约单车
     * @param bicycleNum 单车编号
     * @param userId 用户id
     */
    void appointmentBicycle(int bicycleNum,int userId);


    /**
     * 修改单车状态
     * @param bicycleState
     * @param bicycleNum
     */
    void changeBicycleState(int bicycleState,int bicycleNum);

    /**
     * 校验是否在骑行中
     * @param userId
     * @return
     */
    int checkUnlock(int userId);

    /**
     * 解锁单车
     * @param shareBicycle
     */
    void unlockBicycle(ShareBicycle shareBicycle);

    /**
     * 根据单车编号获取单车位置
     * @param bicycleNum
     * @return
     */
    String getSiteByBicycleNum(int bicycleNum);

    /**
     *  上报故障
     * @param fault
     * @return
     */
    Integer sbikeFault(Fault fault);

    /**
     *  历史故障（用户提交单车的故障）
     * @param userId
     * @return
     */
    List<Fault> historyMalfunction(int userId);

    /**
     * 故障的详情资料
     * @param id
     * @return
     */
    Fault faultDetails(int id);
    /**
     * 根据用户ID查询骑行记录信息
     * @param userId    用户ID
     * @return
     */
    List<ShareBicycle> selectShareBicycleByUserId(int userId);

    /**
     * 根据骑行记录ID查询骑行记录信息
     * @param id    骑行记录ID
     * @return
     */
    ShareBicycle selectShareBicycleById(int id);

    /**
     * 根据用户ID查询用户信息
     * @param userId    用户ID
     * @return
     */
    User selectUserById(int userId);

    /**
     * 根据钱包ID查询钱包信息
     * @param walletId      钱包ID
     * @return
     */
    Wallet selectWalletById(int walletId);

    /**
     * 支付
     * @param wallet    钱包实体类
     * @return
     */
    int updateWalletById(Wallet wallet);

    /**
     * 插入消费记录
     * @param payrecord     消费记录实体类
     * @return
     */
    int insertPayrecord(Payrecord payrecord);

    /**
     * 关锁改变骑行记录
     * @param shareBicycle      骑行记录实体类
     * @return
     */
    int shutUpdateShareBicycle(ShareBicycle shareBicycle);

    /**
     * 上报计费异常
     * @param abnormal      上报计费异常实体类
     * @return
     */
    int reportAbnormalBilling(Abnormal abnormal);

    /**
     * 异常修改骑行记录
     * @param id    骑行记录ID
     * @return
     */
    int abnormalUpdateShareBicycle(int id);

}
