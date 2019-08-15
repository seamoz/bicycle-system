package com.ps.bicycleh5app.controller;

import com.ps.allapp.domain.Result;
import com.ps.bicycleh5app.service.ManagebicycleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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

}
