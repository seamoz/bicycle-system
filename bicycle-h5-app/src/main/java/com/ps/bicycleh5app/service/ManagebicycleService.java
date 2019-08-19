package com.ps.bicycleh5app.service;

import com.ps.allapp.domain.Fault;
import com.ps.allapp.domain.Result;
import com.ps.allapp.domain.ShareBicycle;
import com.ps.allapp.domain.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

/**
 * @author VP
 */
@FeignClient("managebicycle-service")
public interface ManagebicycleService {
    /**
     * 更改地址
     * @param shareBicycle
     * @return
     */
    @GetMapping("/bikes/address")
    Result changeAddress(@RequestBody ShareBicycle shareBicycle);

    /**
     * 初始化首页获取地址和预约的单车
     * @param user
     * @return
     */
    @GetMapping("/bikes/init")
    Result bicycleInit(@RequestBody User user);

    /**
     * 预约单车
     * @param user
     * @return
     */
    @PostMapping("/bikes/subscribe")
    Result appointmentBicycle(@RequestBody User user);

    /**
     * 解锁
     * @param user
     * @return
     */
    @PostMapping("/bikes/unlock")
    Result unlockBicycle(@RequestBody User user);


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

    /**
     *  上报故障
     * @param fault
     * @return
     */
    @PostMapping("/bikes/malfunction")
    Result sbikeFault(Fault fault);


    /**
     * 关锁
     * @param map
     * @return
     */
    @RequestMapping("/bikes/shut")
    Result shut(@RequestBody Map<String, String> map);

    /**
     * 上报计费异常
     * @param map
     * @return
     */
    @RequestMapping("/bikes/abnormal")
    public Result abnormal(@RequestBody Map<String, String> map);

}
