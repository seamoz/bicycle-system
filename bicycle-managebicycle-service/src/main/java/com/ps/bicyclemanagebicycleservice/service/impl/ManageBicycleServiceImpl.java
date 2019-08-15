package com.ps.bicyclemanagebicycleservice.service.impl;

import com.ps.allapp.domain.ShareBicycle;
import com.ps.allapp.domain.User;
import com.ps.allapp.exception.BusinessException;
import com.ps.bicyclemanagebicycleservice.mapper.ManageBicycleMapper;
import com.ps.bicyclemanagebicycleservice.service.ManageBicycleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author ZZH
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

}
