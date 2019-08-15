package com.ps.bicycleh5app.service;

import com.ps.allapp.domain.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

/**
 * @author VP
 */
@FeignClient("managebicycle-service")
public interface ManagebicycleService {

    /**
     * 历史故障（用户提交单车的故障）
     */
    @GetMapping("/history-malfunction")
    Result historyMalfunction(@RequestParam("userId") int userId);

    /**
     * 故障的详情资料
     */
    @GetMapping("/fault-details")
    Result faultDetails(@RequestParam("id") int id);

    /**
     *  故障上报成功查询
     */
    @GetMapping("/bikes/succeed")
    Result succeed(@RequestParam("id") int id);

    /**
     * 骑行中
     * @param map
     * @return
     */
    @RequestMapping("/bikes/cycling")
    Result cycling(@RequestBody Map<String, String> map);

    /**
     * 骑行扣费页
     * @param map
     * @return
     */
    @RequestMapping("/bikes/deduction")
    Result deduction(@RequestBody Map<String, String> map);

    /**
     * 支付
     * @param map
     * @return
     */
    @RequestMapping("/bikes/pay")
    Result pay(@RequestBody Map<String, String> map);

}
