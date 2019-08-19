package com.ps.bicycleh5app.controller;

import com.ps.allapp.domain.Result;
import com.ps.allapp.domain.User;
import com.ps.bicycleh5app.service.BillingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author VP
 */
@RestController
public class BillingController {

    @Autowired
    private BillingService billingService;

    @PostMapping("/paymentCode")
    public Result findPay(@RequestParam("userId") Integer userId,@RequestParam("payPassword") String payPassword){
        Integer pay = billingService.findPay(userId, payPassword);
        Result<Object> objectResult = new Result<>();
        objectResult.setData(pay);
        objectResult.setMeg("成功");
        return objectResult;
    }

    /**
     *  免密支付
     * @param user(userId,password)
     * @return
     */
    @PostMapping("/confidential-payment")
    public Result confidentialPayment(@RequestBody User user){
        System.out.println(user);
        return billingService.confidentialPayment(user);
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
