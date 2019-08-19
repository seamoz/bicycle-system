package com.ps.bicycleh5app.service;

import com.ps.allapp.domain.Result;
import com.ps.allapp.domain.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author VP
 */
@FeignClient("billing-service")
public interface BillingService {

    /**
     *  免密支付
     * @param userId,password
     * @return
     */
    @PostMapping("/billing/confidential-payment")
    public Result confidentialPayment(@RequestBody User user);

    /**
     * 退押金
     * @param userId
     * @return
     */
    @PostMapping("/billing/deposit-refund")
    public Result depositRefund(@RequestParam("userId") int userId);

    /**
     * 交押金
     * @param userId
     * @return
     */
    @PostMapping("/billing/payDeposit")
    public Result payDeposit(@RequestParam("userId") int userId);

    /**
     * 查询押金
     * @param userId
     * @return
     */
    @GetMapping("/billing/inquiryAmount")
    public Result inquiryAmount(@RequestParam("userId") int userId);


}
