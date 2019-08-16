package com.ps.bicyclemanagebicycleservice.mapper;

import com.ps.allapp.domain.Fault;
import com.ps.allapp.domain.ShareBicycle;
import com.ps.allapp.domain.User;
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
}
