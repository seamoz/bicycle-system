package com.ps.bicycleh5app.controller;

import com.ps.allapp.domain.Result;
import com.ps.bicycleh5app.service.BillingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author VP
 */
@RestController
public class BillingController {

    @Autowired
    private BillingService billingService;

    /**
     *  免密支付
     * @param userId,password
     * @return
     */
    @PostMapping("/confidential-payment")
    public Result confidentialPayment(@RequestParam("userId") int userId, @RequestParam("password") String password){
        return billingService.confidentialPayment(userId,password);
    }

    /**
     * 退押金
     * @param userId
     * @return
     */
    @PostMapping("/deposit-refund")
    public Result depositRefund(@RequestParam("userId") int userId){
        return billingService.depositRefund(userId);
    }

    /**
     * 交押金
     * @param userId
     * @return
     */
    @PostMapping("/payDeposit")
    public Result payDeposit(@RequestParam("userId") int userId){
        return billingService.payDeposit(userId);
    }

    /**
     * 查询押金
     * @param userId
     * @return
     */
    @GetMapping("/inquiryAmount")
    public Result inquiryAmount(@RequestParam("userId") int userId){
        return billingService.inquiryAmount(userId);
    }

}
