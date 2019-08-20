package com.ps.bicycleh5app.controller;

import com.ps.allapp.domain.Result;
import com.ps.allapp.domain.User;
import com.ps.allapp.domain.Wallet;
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

    @PostMapping("/alterPaymentCode")
    public Result updatePayPassword(@RequestParam("userId") Integer userId, @RequestParam("payPassword") String payPassword, @RequestParam("payPassword2") String payPassword2) {
        return billingService.updatePayPassword(userId,payPassword,payPassword2);
    }

    @PostMapping("/paymentCode")
    public Result findPay(@RequestParam("userId") Integer userId,@RequestParam("payPassword") String payPassword){
        Integer pay = billingService.findPay(userId, payPassword);
        Result<Object> objectResult = new Result<>();
        objectResult.setData(pay);
        objectResult.setMeg("成功");
        System.out.println(objectResult);
        return objectResult;
    }

    /**
     *  免密支付
     * @param wallet(userId,password)
     * @return
     */
    @PostMapping("/confidential-payment")
    public Result confidentialPayment(@RequestBody Wallet wallet){
        return billingService.confidentialPayment(wallet);
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
