package com.ps.bicycleh5app.controller;

import com.ps.allapp.domain.Result;
import com.ps.bicycleh5app.service.ManagebicycleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @author VP
 */
@RestController
public class ManagebicycleController {

    @Autowired
    private ManagebicycleService managebicycleService;

    /**
     * 历史故障（用户提交单车的故障）
     */
    @GetMapping("/history-malfunction")
    Result historyMalfunction(@RequestParam("userId") int userId){
        Result result = managebicycleService.historyMalfunction(userId);
        return result;
    }

    /**
     * 故障的详情资料
     */
    @GetMapping("/fault-details")
    Result faultDetails(@RequestParam("id") int id){
        Result result = managebicycleService.faultDetails(id);
        return result;
    }

    /**
     *  故障上报成功查询
     */
    @GetMapping("/bikes/succeed")
    Result succeed(@RequestParam("id") int id){
        Result result = managebicycleService.succeed(id);
        return result;
    }

    /**
     * 骑行中
     * @param map
     * @return
     */
    @RequestMapping("/bikes/cycling")
    public Result cycling(@RequestBody Map<String, String> map){
        return managebicycleService.cycling(map);
    }

    /**
     * 骑行扣费页
     * @param map
     * @return
     */
    @RequestMapping("/bikes/deduction")
    public Result deduction(@RequestBody Map<String, String> map){
        return managebicycleService.deduction(map);
    }

    /**
     * 支付
     * @param map
     * @return
     */
    @RequestMapping("/bikes/pay")
    public Result pay(@RequestBody Map<String, String> map){
        return managebicycleService.pay(map);
    }

}
