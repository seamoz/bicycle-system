package com.ps.bicyclemanagebicycleservice.controller;

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

    public void hello(){

    }

    @GetMapping("/bikes/cycling")
    public void cycling(@RequestParam("userId") int userId){
        manageBicycleService.cycling(userId);
    }

    @GetMapping("/bikes/deduction")
    public void deduction(@RequestParam("id") int id){
        manageBicycleService.deduction(id);
    }

    @PostMapping("/bikes/malfunction")
    public void malfunction(){
        manageBicycleService.malfunction();
    }

    @GetMapping("/bikes/succeed")
    public void succeed(@RequestParam("bicycleNum") String bicycleNum){
        manageBicycleService.succeed(bicycleNum);
    }

    @PutMapping("/pays/pay")
    public void pay(@RequestParam("userId") int userId, @RequestParam("money") float money){
        manageBicycleService.pay(userId, money);
    }

}
