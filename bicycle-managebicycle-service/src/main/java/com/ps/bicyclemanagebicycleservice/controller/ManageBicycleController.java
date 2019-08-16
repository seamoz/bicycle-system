package com.ps.bicyclemanagebicycleservice.controller;



import com.ps.allapp.domain.Result;
import com.ps.allapp.domain.ShareBicycle;
import com.ps.allapp.domain.User;
import com.ps.bicyclemanagebicycleservice.service.ManageBicycleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import javax.websocket.server.PathParam;

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

    @GetMapping("/address")
    public Result changeAddress(@RequestBody ShareBicycle shareBicycle) {
        System.out.println(shareBicycle);
        result.setData(manageBicycleServiceImpl.changeAddress(shareBicycle.getBicycleSite()));
        result.setError_code(0);
        result.setMeg("success");
        return result;
    }

    @GetMapping("/init")
    public Result bicycleInit(@RequestBody User user){
        result.setData(manageBicycleServiceImpl.bicycleInit(user.getUserId()));
        result.setError_code(0);
        result.setMeg("success");
        return result;
    }

    @PostMapping("/subscribe")
    public Result appointmentBicycle(@RequestBody User user) {
        manageBicycleServiceImpl.appointmentBicycle(user);
        result.setError_code(0);
        result.setMeg("success");
        return result;
    }

    @PostMapping("/unlock")
    public Result unlockBicycle(@RequestBody User user){
        manageBicycleServiceImpl.unlockBicycle(user);
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


