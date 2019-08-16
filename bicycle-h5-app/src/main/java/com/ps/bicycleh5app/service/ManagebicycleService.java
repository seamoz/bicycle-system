package com.ps.bicycleh5app.service;

import com.ps.allapp.domain.Fault;
import com.ps.allapp.domain.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author VP
 */
@FeignClient("managebicycle-service")
public interface ManagebicycleService {

    /**
     * 历史故障（用户提交单车的故障）
     */
    @GetMapping("/bikes/history-malfunction")
    Result historyMalfunction(@RequestParam("userId") int userId);

    /**
     * 故障的详情资料
     */
    @GetMapping("/bikes/fault-details")
    Result faultDetails(@RequestParam("id") int id);

    /**
     *  故障上报成功查询
     */
    @GetMapping("/bikes/succeed")
    Result succeed(@RequestParam("id") int id);

    /**
     *  上报故障
     * @param fault
     * @return
     */
    @PostMapping("/bikes/malfunction")
    Result sbikeFault(Fault fault);
}
