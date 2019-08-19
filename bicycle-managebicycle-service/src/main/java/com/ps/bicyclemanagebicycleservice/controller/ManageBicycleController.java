package com.ps.bicyclemanagebicycleservice.controller;



import com.ps.allapp.domain.Fault;
import com.ps.allapp.domain.Result;
import com.ps.allapp.domain.ShareBicycle;
import com.ps.allapp.domain.User;
import com.ps.bicyclemanagebicycleservice.service.ManageBicycleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @author ZZH
 * @date 2019/8/13 19:17
 */
@RestController
@RequestMapping("/bikes")
public class ManageBicycleController {

    private Result result = new Result();

    @Autowired
    private ManageBicycleService manageBicycleServiceImpl;

    @RequestMapping(value = "/address",method = RequestMethod.POST)
    public Result changeAddress(@RequestParam("bicycleSite") String bicycleSite) {
        result.setData(manageBicycleServiceImpl.changeAddress(bicycleSite));
        result.setError_code(0);
        result.setMeg("success");
        return result;
    }

    @RequestMapping(value = "/init",method = RequestMethod.POST)
    public Result bicycleInit(@RequestParam("userId") int userId){
        result.setData(manageBicycleServiceImpl.bicycleInit(userId));
        result.setError_code(0);
        result.setMeg("success");
        return result;
    }

    @RequestMapping(value = "/subscribe",method = RequestMethod.POST)
    public Result appointmentBicycle(@RequestParam("userId") int userId,@RequestParam("bicycleNum") int bicycleNum) {

        manageBicycleServiceImpl.appointmentBicycle(userId,bicycleNum);

        result.setError_code(0);
        result.setMeg("预约成功");
        return result;
    }

    @RequestMapping(value = "/unlock",method = RequestMethod.POST)
    public Result unlockBicycle(@RequestParam("userId") int userId,@RequestParam("bicycleNum") int bicycleNum){
        manageBicycleServiceImpl.unlockBicycle(userId,bicycleNum);
        result.setError_code(0);
        result.setMeg("解锁成功");
        return result;
    }

    /**
     * 故障的详情资料
     */
    @GetMapping("/fault-details")
    public Result faultDetails(@RequestParam("id") int id){
        return manageBicycleServiceImpl.faultDetails(id);
    }

    /**
     *  故障上报成功查询
     */
    @GetMapping("/succeed")
    public Result succeed(@RequestParam("id") int id){
        return manageBicycleServiceImpl.faultDetails(id);
    }

    /**
     *  上报故障
     * @param fault
     * @return
     */
    @PostMapping("/malfunction")
    public Result sbikeFault(@RequestBody Fault fault){
        return manageBicycleServiceImpl.sbikeFault(fault);
    }

    /**
     * 历史故障（用户提交单车的故障）
     */
    @GetMapping("/history-malfunction")
    public Result historyMalfunction(@RequestParam("userId") int userId){
        return manageBicycleServiceImpl.historyMalfunction(userId);
    }

    /**
     * 骑行中
     * @param map
     * @return
     */
    @RequestMapping("/cycling")
    public Result cycling(@RequestBody Map<String, String> map){
        return manageBicycleServiceImpl.cycling(Integer.valueOf(map.get("userId")));
    }

    /**
     * 骑行扣费页
     * @param map
     * @return
     */
    @RequestMapping("/deduction")
    public Result deduction(@RequestBody Map<String, String> map){
        return manageBicycleServiceImpl.deduction(Integer.valueOf(map.get("id")));
    }

    /**
     * 支付
     * @param map
     * @return
     */
    @RequestMapping("/pay")
    public Result pay(@RequestBody Map<String, String> map){
        return manageBicycleServiceImpl.pay(Integer.valueOf(map.get("userId")), Float.valueOf(map.get("money")), map.get("payPassword"), map.get("payType"));
    }

}


