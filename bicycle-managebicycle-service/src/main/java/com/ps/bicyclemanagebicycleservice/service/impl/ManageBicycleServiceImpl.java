package com.ps.bicyclemanagebicycleservice.service.impl;

import com.ps.allapp.domain.Fault;
import com.ps.allapp.domain.Result;
import com.ps.allapp.domain.ShareBicycle;
import com.ps.allapp.domain.User;
import com.ps.allapp.exception.BusinessException;
import com.ps.bicyclemanagebicycleservice.mapper.ManageBicycleMapper;
import com.ps.bicyclemanagebicycleservice.service.ManageBicycleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author VP
 */
@Service
public class ManageBicycleServiceImpl implements ManageBicycleService {

    @Autowired
    private ManageBicycleMapper manageBicycleMapper;

    @Override
    public List<ShareBicycle> changeAddress(String address){
        return manageBicycleMapper.changeAddress(address);
    }

    @Override
    public void appointmentBicycle(User user) {
        int bicycleNum = checkAppointmentBicycle(user.getId());
        if(bicycleNum!=0){
            throw new BusinessException(500, "您已经预约过单车了");
        }
        manageBicycleMapper.appointmentBicycle(user.getBicycleNum(),user.getId());
        manageBicycleMapper.changeBicycleState(user.getBicycleNum(),1);
    }

    @Override
    public User bicycleInit(int userId) {
        return manageBicycleMapper.bicycleInit(userId);
    }

    @Override
    public int checkAppointmentBicycle(int userId) {
        return manageBicycleMapper.checkAppointment(userId).getBicycleNum();
    }

    @Override
    public void unlockBicycle(User user){
        if(manageBicycleMapper.checkUnlock(user.getId()) != 0){
            throw new BusinessException(500, "您有一个订单正在进行！");
        }
        //是否预约了单车
        int bicycleNum = checkAppointmentBicycle(user.getId());
        if(bicycleNum!=0 && user.getBicycleNum() == bicycleNum){
            if(user.getBicycleNum() != bicycleNum){
                throw new BusinessException(500, "您已经预约过一辆车了！");
            }
        }
        /*#{userId},#{bicycleNum},#{initialAddress},#{startTime}*/
        //获取当前时间
        String nowTime = new SimpleDateFormat("yyyy-MM-dd :hh:mm:ss").format(new Date());
        //根据单车编号获取位置
        String site = manageBicycleMapper.getSiteByBicycleNum(user.getBicycleNum());
        System.out.println(site);
        manageBicycleMapper.unlockBicycle(new ShareBicycle(user.getId(),user.getBicycleNum(),site,nowTime));
    }

    /**
     * 历史故障（用户提交单车的故障）
     * @param userId
     * @return
     */
    @Override
    public Result historyMalfunction(int userId) {
        Result result = new Result();

        List<Fault> list = manageBicycleMapper.historyMalfunction(userId);

        result.setError_code(200);

        if (list == null || list.size() <= 0){
            result.setMeg("该用户没有提交单车故障");
            return result;
        }

        result.setData(list);
        result.setMeg("详情资料如下：");
        return result;
    }

    /**
     * 故障的详情资料
     * @param id
     * @return
     */
    @Override
    public Result faultDetails(int id) {
        Result result = new Result();

        Fault fault = manageBicycleMapper.faultDetails(id);
        result.setError_code(200);

        if (fault == null){
            result.setMeg("该单车没有故障资料...");
            return result;
        }

        result.setData(fault);
        result.setMeg("详情资料如下:");
        return result;
    }

    /**
     * 上报故障
     * @param fault
     * @return
     */
    @Override
    public Result sbikeFault(Fault fault){
        Result result = new Result();

        /* 确认传入的参数是否为空，或者值有误 */
        if(fault.getFaultType() == null ||
                fault.getBicycleNum() == null ||
                fault.getUserId() <= 0){
            result.setError_code(102);
            result.setMeg("故障类型、单车编号不能为空，请重新输入...");
            return result;
        }

        Integer integer = manageBicycleMapper.sbikeFault(fault);

        if(integer == null || fault.getId() <= 0 || integer == 0){
            result.setError_code(103);
            result.setMeg("网络异常，请销后再试...");
            return result;
        }

        Map<String, Integer> map = new HashMap<>();
        map.put("id",fault.getId());

        result.setData(map);
        result.setMeg("故障发送成功！");
        result.setError_code(200);
        return result;

    }

}
