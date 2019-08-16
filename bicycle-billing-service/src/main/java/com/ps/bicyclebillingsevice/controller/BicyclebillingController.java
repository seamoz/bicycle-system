package com.ps.bicyclebillingsevice.controller;

import com.ps.allapp.domain.Result;
import com.ps.bicyclebillingsevice.service.BillingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author VP
 */
@RestController
@RequestMapping("/billing")
public class BicyclebillingController {

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
        return billingService.RefundPayDeposit(userId,"退押金");
    }

    /**
     * 交押金
     * @param userId
     * @return
     */
    @PostMapping("/payDeposit")
    public Result payDeposit(@RequestParam("userId") int userId){
        return billingService.RefundPayDeposit(userId,"交押金");
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
