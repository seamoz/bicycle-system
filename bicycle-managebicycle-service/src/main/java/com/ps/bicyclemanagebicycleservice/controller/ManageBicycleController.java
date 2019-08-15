package com.ps.bicyclemanagebicycleservice.controller;

import com.ps.allapp.domain.Result;
import com.ps.bicyclemanagebicycleservice.service.ManageBicycleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author ZZH
 * @date 2019/8/13 19:17
 */
@RestController
public class ManageBicycleController {

    @Autowired
    private ManageBicycleService manageBicycleService;

    @GetMapping("/bicycle/address")
    public void changeAddress(){

    }

    @PostMapping("/bicycle/subscribe")
    public void reserveBicycle(){

    }

    @PostMapping("/bicycle/unlock")
    public void unlockBicycle(){

    }

    @GetMapping("/bikes/cycling")
    public void cycling(@RequestParam("userId") int userId){
        manageBicycleService.cycling(userId);
    }

    @GetMapping("/bikes/deduction")
    public void deduction(@RequestParam("id") int id){
        manageBicycleService.deduction(id);
        System.out.println("ddd");
    }

    @PostMapping("/bikes/malfunction")
    public void malfunction(){
        manageBicycleService.malfunction();
    }

    @PutMapping("/pays/pay")
    public void pay(@RequestParam("userId") int userId, @RequestParam("money") float money){
        manageBicycleService.pay(userId, money);
    }

    /**
     * 历史故障（用户提交单车的故障）
     */
    @GetMapping("/history-malfunction")
    public Result historyMalfunction(@RequestParam("userId") int userId){

        Result result  = manageBicycleService.historyMalfunction(userId);
        return result;
    }

    /**
     * 故障的详情资料
     */
    @GetMapping("/fault-details")
    public Result faultDetails(@RequestParam("id") int id){

        Result result  = manageBicycleService.faultDetails(id);
        return result;
    }

    /**
     *  故障上报成功查询
     */
    @GetMapping("/bikes/succeed")
    public Result succeed(@RequestParam("id") int id){

        Result result  = manageBicycleService.faultDetails(id);
        return result;
    }




}
