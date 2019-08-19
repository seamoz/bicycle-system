package com.ps.bicyclebillingsevice.controller;

import com.ps.allapp.domain.Result;
import com.ps.allapp.domain.User;
import com.ps.allapp.domain.Wallet;
import com.ps.bicyclebillingsevice.service.BillingService;
import com.ps.bicyclebillingsevice.service.WalletService;
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
     * @param user
     * @return
     */
    @PostMapping("/confidential-payment")
    public Result confidentialPayment(@RequestBody User user){
        return billingService.confidentialPayment(user);
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
