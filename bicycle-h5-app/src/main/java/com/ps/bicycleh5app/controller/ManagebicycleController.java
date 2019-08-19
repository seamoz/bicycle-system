package com.ps.bicycleh5app.controller;

import com.ps.allapp.domain.Fault;
import com.ps.allapp.domain.Result;
import com.ps.allapp.domain.ShareBicycle;
import com.ps.allapp.domain.User;
import com.ps.bicycleh5app.service.ManagebicycleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @author VP
 */
@RestController
@RequestMapping("/bikes")
public class ManagebicycleController {

    @Autowired
    private ManagebicycleService managebicycleService;


    @GetMapping("/address")
    public Result changeAddress(@RequestBody ShareBicycle shareBicycle) {
        return managebicycleService.changeAddress(shareBicycle);
    }

    @GetMapping("/init")
    public Result bicycleInit(@RequestBody User user){
        return managebicycleService.bicycleInit(user);
    }

    @PostMapping("/subscribe")
    public Result appointmentBicycle(@RequestBody User user) {
        return managebicycleService.appointmentBicycle(user);
    }

    @PostMapping("/unlock")
    public Result unlockBicycle(@RequestBody User user){
        return managebicycleService.unlockBicycle(user);
    }

    /**
     * 历史故障（用户提交单车的故障）
     */
    @GetMapping("/history-malfunction")
    Result historyMalfunction(@RequestParam("userId") int userId){
        return managebicycleService.historyMalfunction(userId);
    }

    /**
     * 故障的详情资料
     */
    @GetMapping("/fault-details")
    Result faultDetails(@RequestParam("id") int id){
        return managebicycleService.faultDetails(id);
    }

    /**
     *  故障上报成功查询
     */
    @GetMapping("/succeed")
    Result succeed(@RequestParam("id") int id){
        return managebicycleService.succeed(id);
    }

    /**
     *  上报故障
     * @param fault
     * @return
     */
    @PostMapping("/malfunction")
    Result sbikeFault(@RequestBody Fault fault){
        return managebicycleService.sbikeFault(fault);
    }

    /**
     * 骑行中
     * @param map
     * @return
     */
    @RequestMapping("/cycling")
    public Result cycling(@RequestBody Map<String, String> map){
        return managebicycleService.cycling(map);
    }

    /**
     * 骑行扣费页
     * @param map
     * @return
     */
    @RequestMapping("/deduction")
    public Result deduction(@RequestBody Map<String, String> map){
        return managebicycleService.deduction(map);
    }

    /**
     * 支付
     * @param map
     * @return
     */
    @RequestMapping("/pay")
    public Result pay(@RequestBody Map<String, String> map){
        return managebicycleService.pay(map);
    }

    /**
     * 关锁
     * @param map
     * @return
     */
    @RequestMapping("/shut")
    public Result shut(@RequestBody Map<String, String> map){
        return managebicycleService.shut(map);
    }

    /**
     * 上报计费异常
     * @param map
     * @return
     */
    @RequestMapping("/abnormal")
    public Result abnormal(@RequestBody Map<String, String> map){
        return managebicycleService.abnormal(map);
    }

}
