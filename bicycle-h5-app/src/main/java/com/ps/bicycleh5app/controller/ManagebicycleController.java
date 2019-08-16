package com.ps.bicycleh5app.controller;

import com.ps.allapp.domain.Fault;
import com.ps.allapp.domain.Result;
import com.ps.bicycleh5app.service.ManagebicycleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
        System.out.println(userId);
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

        System.out.println("hahahah");
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

}
