package com.ps.bicyclemanagebicycleservice.service;

import com.ps.allapp.domain.Fault;
import com.ps.allapp.domain.Result;
import com.ps.bicyclemanagebicycleservice.mapper.ManageBicycleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ManageBicycleService {

    @Autowired
    private ManageBicycleMapper manageBicycleMapper;

    public void cycling(int userId){
        System.out.println("sss");
    }

    public void deduction(int id){

    }

    public void malfunction(){

    }

    public void succeed(String bicycleNum){

    }

    public void pay(int userId, float money){

    }

    /**
     * 历史故障（用户提交单车的故障）
     * @param userId
     * @return
     */
    public Result historyMalfunction(int userId) {
        Result result = new Result();

//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日 HH时mm分ss秒");

        List<Fault> list = manageBicycleMapper.historyMalfunction(userId);

        result.setData(list);
        return result;
    }

    /**
     * 故障的详情资料
     * @param id
     * @return
     */
    public Result faultDetails(int id) {
        Result result = new Result();

        Fault fault = manageBicycleMapper.faultDetails(id);

        result.setData(fault);
        return result;
    }


}
